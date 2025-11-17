from database.database_service import DatabaseService as db

class MovieFilters():
    @staticmethod
    def movies_simple_info():
        query = """
            SELECT 
                movie.id_movie,
                movie.movie_title, 
                movie.movie_poster, 
                AVG(review.review_rating) as movie_avg_rating,
                movie.publication_year, 
                (
                    SELECT GROUP_CONCAT(genre.genre SEPARATOR ', ')
                    FROM genre 
                    INNER JOIN movie_genre ON movie_genre.id_genre = genre.id_genre
                    WHERE movie_genre.id_movie = movie.id_movie
                ) AS genres,

                (
                    SELECT GROUP_CONCAT(director.`director_name` SEPARATOR ", ")
                    FROM director
                    INNER JOIN movie_director ON movie_director.id_director = director.id_director
                    WHERE movie_director.id_movie = movie.id_movie
                ) AS directors,
                (
                    SELECT GROUP_CONCAT(actor.`actor_name` SEPARATOR ", ")
                    FROM actor
                    INNER JOIN movie_actor ON movie_actor.id_actor = actor.id_actor
                    WHERE movie_actor.id_movie = movie.id_movie
                ) AS actors
            FROM movie
            INNER JOIN review ON review.id_movie = movie.id_movie
            GROUP BY 
                movie.id_movie, 
                movie.movie_title, 
                movie.movie_poster, 
                movie.publication_year
            ORDER BY movie.movie_title ASC;
        """
        
        with db.session() as session:
            session.execute("USE cinescope;")
            session.execute(query)
            result = session.fetchall()
            
        movies_json = []
        for res in result:
            movies_json.append({
                "id_movie": res[0],
                "movie_title": res[1],
                "movie_poster": res[2],
                "avg_rating": float(f"{res[3]:.1f}") if res[3] is not None else None,
                "publication_year": res[4],
                "genres": res[5],
                "directors": res[6],
                "actors": res[7]
        })
            
        return movies_json
    
    @staticmethod
    def movie_simple_info(id_movie):
        query = """
        SELECT 
            movie.id_movie,
            movie.movie_title, 
            movie.movie_poster, 
            AVG(review.review_rating) as movie_avg_rating,
            movie.publication_year,
            movie.duration_time, 
            movie.movie_synopsis,
            (SELECT genre.genre 
            FROM genre
            INNER JOIN movie_genre ON movie_genre.id_genre = genre.id_genre
            WHERE movie.id_movie = movie_genre.id_movie
            LIMIT 1) AS genre,
            (SELECT GROUP_CONCAT(director.`director_name` SEPARATOR ", ")
            FROM director
            INNER JOIN movie_director ON movie_director.id_director = director.id_director
            WHERE movie_director.id_movie = movie.id_movie) AS directors
        FROM movie
        INNER JOIN review ON review.id_movie = movie.id_movie
        WHERE movie.id_movie = %s
        GROUP BY 
            movie.id_movie;       
        """
        
        with db.session() as session:
            session.execute("USE cinescope;")
            session.execute(query, (id_movie,))
            result = session.fetchone()
            
        movie_json = {
            "id_movie": result[0],
            "movie_title": result[1],
            "movie_poster": result[2],
            "avg_rating": float(f"{result[3]:.1f}") if result[3] is not None else None,
            "publication_year": result[4],
            "duration_time": str(result[5]),
            "movie_synopsis": result[6],
            "genre": result[7],
            "directors": result[8]
        }
        
        return movie_json
    
    @staticmethod
    def movies_related(genre, movie_title):
        query = """
        SELECT movie.id_movie, movie.movie_title, movie.movie_poster, AVG(review.review_rating) as movie_avg_rating, movie.publication_year, genre.genre
        FROM genre
        INNER JOIN movie_genre ON movie_genre.id_genre = genre.id_genre
        INNER JOIN movie ON movie.id_movie = movie_genre.id_movie
        INNER JOIN review ON review.id_movie = movie.id_movie
        WHERE genre.id_genre = movie_genre.id_genre
        AND genre = %s
        AND movie.movie_title != %s
        GROUP BY movie.id_movie;
        """
        
        with db.session() as session:
            session.execute("USE cinescope;")
            session.execute(query, (genre, movie_title))
            result = session.fetchall()
        
        movies_json = []
        for res in result:
            movies_json.append({
                "id_movie": res[0],
                "movie_title": res[1],
                "movie_poster": res[2],
                "avg_rating": float(f"{res[3]:.1f}") if res[3] is not None else None,
                "publication_year": res[4],
                "genre": res[5]
            })
        
        return movies_json
    
    @staticmethod
    def movies_adm_list():
        query = """
        SELECT 
            movie.id_movie,
            movie.movie_title, 
            AVG(review.review_rating) as movie_avg_rating
        FROM movie
        INNER JOIN review ON review.id_movie = movie.id_movie
        GROUP BY 
            movie.id_movie;
        """
        
        with db.session() as session:
            session.execute("USE cinescope;")
            session.execute(query)
            result = session.fetchall()
        
        movies_json = []
        for res in result:
            movies_json.append({
                "id_movie": res[0],
                "movie_title": res[1],
                "avg_rating": float(f"{res[2]:.1f}") if res[2] is not None else None,
            })
        
        return movies_json