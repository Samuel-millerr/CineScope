from http import HTTPStatus

import requests

TEST_URL = "http://localhost:8001/api/"


def test_read_root_and_return_api_running_sucessfully(server):
    response = requests.get(TEST_URL)

    assert response.status_code == HTTPStatus.OK
    assert response.json() == {"message": "API running sucessfully"}
