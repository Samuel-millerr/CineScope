from cinescope.api.models.review import ReviewModel
from cinescope.api.services._base_service import BaseService


class ReviewService(BaseService[ReviewModel]):
    ...


review_service = ReviewService(ReviewModel)
