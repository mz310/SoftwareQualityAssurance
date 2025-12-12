from datetime import datetime
from typing import List, Optional
from uuid import uuid4

# In-memory store for todos. This keeps the data handling separate from
# FastAPI/Pydantic so tools like Pynguin can exercise pure Python logic.
todos: List[dict] = [
    {
        "id": str(uuid4()),
        "title": "Learn FastAPI",
        "description": "Go through the official FastAPI documentation and tutorials.",
        "completed": False,
        "created_at": datetime.now(),
    },
    {
        "id": str(uuid4()),
        "title": "Build a Todo API",
        "description": "Create a REST API for managing todo items using FastAPI.",
        "completed": False,
        "created_at": datetime.now(),
    },
    {
        "id": str(uuid4()),
        "title": "Write blog post",
        "description": "Draft a blog post about creating a Todo API with FastAPI.",
        "completed": False,
        "created_at": datetime.now(),
    },
]


def create_todo_record(
    title: str, description: Optional[str] = None, completed: bool = False
) -> dict:
    todo = {
        "id": str(uuid4()),
        "title": title,
        "description": description,
        "completed": completed,
        "created_at": datetime.now(),
    }
    todos.append(todo)
    return todo


def list_todos() -> List[dict]:
    return todos


def find_todo(todo_id: str) -> Optional[dict]:
    for todo in todos:
        if todo["id"] == todo_id:
            return todo
    return None


def update_todo_record(
    todo_id: str, title: str, description: Optional[str], completed: bool
) -> dict:
    todo = find_todo(todo_id)
    if not todo:
        raise KeyError("Todo not found")
    todo["title"] = title
    todo["description"] = description
    todo["completed"] = completed
    return todo


def delete_todo_record(todo_id: str) -> None:
    todo = find_todo(todo_id)
    if not todo:
        raise KeyError("Todo not found")
    todos.remove(todo)
