from core.handlers.base_handler import BaseHandler
from database.database_service import DatabaseService as db
from core.settings import config
from views.auth_view import AuthHandler

status = config.status

class ReviewHandler(BaseHandler):
    def get_reviews_by_movie(self, handler, id_movie: int):
        query = """
        SELECT user.user_name, review.review_text, review.review_rating, review.review_date
        FROM review
        JOIN user ON user.id_user = review.id_user
        WHERE id_movie = %s;
        """
        
        with db.session() as session:
            session.execute("USE cinescope;")
            session.execute(query, (id_movie,))
            result = session.fetchall()
            
        reviews_json = []
        for res in result:
            reviews_json.append({
                "user_name": res[0],
                "review_text": res[1],
                "review_rating": float(f"{res[2]:.1f}") if res[2] is not None else 0,
                "review_date": res[3].isoformat() if res[3] is not None else None 
            })
            
        handler.send_json_response(reviews_json, status["HTTP_200_OK"])
        
    def get_reviews_by_user(self, handler, user):
        id_user = AuthHandler.get_user_id(user)

        query = """
            SELECT movie.movie_poster, movie.movie_title, review.review_text, review.review_rating
            FROM review
            JOIN movie ON movie.id_movie = review.id_movie
            JOIN user ON user.id_user = review.id_user
            WHERE review.id_user = %s;
        """

        with db.session() as session:
            session.execute("USE cinescope;")
            session.execute(query, (id_user["id_user"],))
            result = session.fetchall()

        requests_json = []
        for res in result:
            requests_json.append({
                "movie_poster": res[0],
                "movie_title": res[1],
                "review_text": res[2],
                "request_body": float(f"{res[3]:.1f}") if res[3] is not None else 0
            })

        handler.send_json_response(requests_json, status["HTTP_200_OK"])