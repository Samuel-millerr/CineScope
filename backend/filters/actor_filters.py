from database.database_service import DatabaseService as db

class ActorFilters():
    @staticmethod
    def actor_by_movie(id_movie):
        query = """
            SELECT actor.actor_name, actor.actor_photo
            FROM actor
            INNER JOIN movie_actor ON movie_actor.id_actor = actor.id_actor
            INNER JOIN movie ON movie.id_movie = movie_actor.id_movie
            WHERE 
                movie_actor.id_movie = %s;
        """
        
        with db.session() as session:
            session.execute("USE cinescope;")
            session.execute(query, (id_movie,))
            result = session.fetchall()
            
        actor_json = [] 
        for res in result:
            actor_json.append({
            "actor_name": res[0],
            "actor_photo": res[1]
        })
        
        return actor_json