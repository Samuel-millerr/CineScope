from cinescope.api.models.request import RequestModel
from cinescope.api.services._base_service import BaseService


class RequestService(BaseService[RequestModel]):
    ...


request_service = RequestService(RequestModel)
