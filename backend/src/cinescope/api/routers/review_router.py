from http import HTTPStatus

from cinescope.api.services.review_service import review_service
from cinescope.infra.db.deps import get_session
from cinescope.infra.server.settings import settings

serverType = settings.server


class ReviewRouter:
    @staticmethod
    def post_review(server: serverType, method: str = "POST"):
        try:
            data = server.parse_json_body()
            with get_session() as db:
                review_service.create(data, db)
                server.send_json_response(
                    data, HTTPStatus.CREATED
                )
        except Exception as e:
            server.send_status_only(HTTPStatus.INTERNAL_SERVER_ERROR)
            raise e

    @staticmethod
    def get_reviews(server: serverType, method="GET"):
        try:
            with get_session() as db:
                reviews = review_service.get_all(db)
                server.send_json_response(reviews)
        except Exception as e:
            server.send_status_only(HTTPStatus.INTERNAL_SERVER_ERROR)
            raise e


review_router = ReviewRouter()
