from http import HTTPStatus

import requests

TEST_URL = "http://localhost:8001/api/actor/"


def test_post_actor_return_actor_json(server):
    response = requests.post(
        TEST_URL,
        json={
            "actor_name": "Test Actor",
            "actor_photo": "http://test_actor_photo"
        }
        )

    assert response.status_code == HTTPStatus.CREATED
    assert response.json() == {
        "actor_name": "Test Actor",
        "actor_photo": "http://test_actor_photo"
    }
