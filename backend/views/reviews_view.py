from core.handlers.base_handler import BaseHandler
from database.database_service import DatabaseService as db
from core.settings import config

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