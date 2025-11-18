from database.database_service import DatabaseService as db

class MovieFilters:
    @staticmethod
    def movies_simple_info():
        query = """
            SELECT 
                m.id_movie,
                m.movie_title,
                m.movie_poster,
                COALESCE(AVG(r.review_rating), 0) AS avg_rating,
                m.publication_year,
                (SELECT GROUP_CONCAT(DISTINCT g.genre SEPARATOR ', ')
                 FROM movie_genre mg
                 JOIN genre g ON g.id_genre = mg.id_genre
                 WHERE mg.id_movie = m.id_movie) AS genres,
                (SELECT GROUP_CONCAT(DISTINCT d.director_name SEPARATOR ', ')
                 FROM movie_director md
                 JOIN director d ON d.id_director = md.id_director
                 WHERE md.id_movie = m.id_movie) AS directors,
                (SELECT GROUP_CONCAT(DISTINCT a.actor_name SEPARATOR ', ')
                 FROM movie_actor ma
                 JOIN actor a ON a.id_actor = ma.id_actor
                 WHERE ma.id_movie = m.id_movie) AS actors
            FROM movie m
            LEFT JOIN review r ON r.id_movie = m.id_movie
            GROUP BY m.id_movie
            ORDER BY m.movie_title ASC;
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
                "avg_rating": float(f"{res[3]:.1f}") if res[3] is not None else 0,
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
                m.id_movie,
                m.movie_title,
                m.movie_poster,
                COALESCE(AVG(r.review_rating), 0) AS avg_rating,
                m.publication_year,
                m.duration_time,
                m.movie_synopsis,
                (SELECT GROUP_CONCAT(DISTINCT g.genre SEPARATOR ', ')
                 FROM movie_genre mg
                 JOIN genre g ON g.id_genre = mg.id_genre
                 WHERE mg.id_movie = m.id_movie) AS genres,
                (SELECT GROUP_CONCAT(DISTINCT d.director_name SEPARATOR ', ')
                 FROM movie_director md
                 JOIN director d ON d.id_director = md.id_director
                 WHERE md.id_movie = m.id_movie) AS directors,
                (SELECT GROUP_CONCAT(DISTINCT a.actor_name SEPARATOR ', ')
                FROM movie_actor ma
                JOIN actor a ON a.id_actor = ma.id_actor
                WHERE ma.id_movie = m.id_movie) AS actors
            FROM movie m
            LEFT JOIN review r ON r.id_movie = m.id_movie
            WHERE m.id_movie = %s
            GROUP BY m.id_movie;
        """
        with db.session() as session:
            session.execute("USE cinescope;")
            session.execute(query, (id_movie,))
            result = session.fetchone()

        if not result:
            return None

        movie_json = {
            "id_movie": result[0],
            "movie_title": result[1],
            "movie_poster": result[2],
            "avg_rating": float(f"{result[3]:.1f}") if result[3] is not None else 0,
            "publication_year": result[4],
            "duration_time": str(result[5]),
            "movie_synopsis": result[6],
            "genre": result[7],
            "directors": result[8],
            "actors": result[9]
        }

        return movie_json

    @staticmethod
    def movies_related(genre, movie_title):
        query = """
            SELECT 
                m.id_movie,
                m.movie_title,
                m.movie_poster,
                COALESCE(AVG(r.review_rating), 0) AS avg_rating,
                m.publication_year,
                (SELECT GROUP_CONCAT(DISTINCT g2.genre SEPARATOR ', ')
                FROM movie_genre mg2
                JOIN genre g2 ON g2.id_genre = mg2.id_genre
                WHERE mg2.id_movie = m.id_movie) AS genres
            FROM movie m
            LEFT JOIN movie_genre mg ON mg.id_movie = m.id_movie
            LEFT JOIN genre g ON g.id_genre = mg.id_genre
            LEFT JOIN review r ON r.id_movie = m.id_movie
            WHERE g.genre = %s
            AND m.movie_title != %s
            GROUP BY m.id_movie
            ORDER BY avg_rating DESC;
        """
        with db.session() as session:
            session.execute("USE cinescope;")
            session.execute(query, (genre.split(", ")[0], movie_title))
            result = session.fetchall()

        movies_json = []
        for res in result:
            movies_json.append({
                "id_movie": res[0],
                "movie_title": res[1],
                "movie_poster": res[2],
                "avg_rating": float(f"{res[3]:.1f}") if res[3] is not None else 0,
                "publication_year": res[4],
                "genre": res[5].split(", ")[0]
            })
            
        return movies_json

    @staticmethod
    def movies_adm_list():
        query = """
            SELECT 
                m.id_movie,
                m.movie_title,
                COALESCE(AVG(r.review_rating), 0) AS movie_avg_rating
            FROM movie m
            LEFT JOIN review r ON r.id_movie = m.id_movie
            GROUP BY m.id_movie;
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
                "avg_rating": float(f"{res[2]:.1f}") if res[2] is not None else 0
            })

        return movies_json
