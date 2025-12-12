import copy
import uuid

import pytest
from fastapi.testclient import TestClient

import main
import service


@pytest.fixture(autouse=True)
def reset_todos():
    snapshot = copy.deepcopy(service.todos)
    yield
    service.todos.clear()
    service.todos.extend(snapshot)


@pytest.fixture
def client():
    return TestClient(main.app)


def test_get_all_todos_returns_initial_items(client):
    response = client.get("/todos/")
    assert response.status_code == 200
    data = response.json()
    assert isinstance(data, list)
    assert len(data) == len(service.todos)
    assert {entry["id"] for entry in data} == {todo["id"] for todo in service.todos}


def test_create_todo_endpoint_creates_entry(client):
    payload = {"title": "Write tests", "description": "cover API", "completed": True}
    response = client.post("/todos/", json=payload)
    assert response.status_code == 200
    body = response.json()
    assert body["title"] == payload["title"]
    assert body["description"] == payload["description"]
    assert body["completed"] is True
    assert uuid.UUID(body["id"])
    assert any(todo["id"] == body["id"] for todo in service.todos)


def test_create_todo_uses_default_completed_flag(client):
    payload = {"title": "Default completed flag", "description": None}
    response = client.post("/todos/", json=payload)
    assert response.status_code == 200
    body = response.json()
    assert body["completed"] is False


def test_get_todo_not_found_returns_404(client):
    missing_id = str(uuid.uuid4())
    response = client.get(f"/todos/{missing_id}")
    assert response.status_code == 404
    assert response.json() == {"detail": "Todo not found"}


def test_update_todo_success(client):
    created = service.create_todo_record("Initial", "desc", False)
    payload = {"title": "Updated", "description": "changed", "completed": True}
    response = client.put(f"/todos/{created['id']}", json=payload)
    assert response.status_code == 200
    updated = response.json()
    assert updated["id"] == created["id"]
    assert updated["title"] == payload["title"]
    assert updated["description"] == payload["description"]
    assert updated["completed"] is True


def test_update_todo_not_found(client):
    payload = {"title": "Updated", "description": "changed", "completed": True}
    response = client.put(f"/todos/{uuid.uuid4()}", json=payload)
    assert response.status_code == 404
    assert response.json()["detail"] == "Todo not found"


def test_delete_todo_success(client):
    todo = service.create_todo_record("Delete me", None, False)
    response = client.delete(f"/todos/{todo['id']}")
    assert response.status_code == 200
    assert response.json()["detail"] == "Todo deleted successfully"
    assert service.find_todo(todo["id"]) is None


def test_delete_todo_not_found(client):
    response = client.delete(f"/todos/{uuid.uuid4()}")
    assert response.status_code == 404
    assert response.json()["detail"] == "Todo not found"
