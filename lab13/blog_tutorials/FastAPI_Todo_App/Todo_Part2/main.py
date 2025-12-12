from datetime import datetime
from typing import List, Optional

from fastapi import FastAPI, HTTPException
from fastapi.responses import JSONResponse
from pydantic import BaseModel

from service import (
    create_todo_record,
    delete_todo_record,
    find_todo,
    list_todos,
    todos,
    update_todo_record,
)

app = FastAPI()

# TodoCreate model for input validation
class TodoCreate(BaseModel):
    title: str
    description: Optional[str] = None
    completed: bool = False


# Todo model for output
class Todo(BaseModel):
    id: str
    title: str
    description: Optional[str] = None
    completed: bool
    created_at: datetime


# Helper function to find a todo by ID
def get_todo_by_id(todo_id: str):
    return find_todo(todo_id)


# Create a new todo
@app.post("/todos/", response_model=Todo)
def create_todo(todo: TodoCreate):
    new_todo = create_todo_record(
        title=todo.title,
        description=todo.description,
        completed=todo.completed,
    )
    return Todo(**new_todo)


# Retrieve all todos
@app.get("/todos/", response_model=List[Todo])
def get_all_todos():
    return list_todos()


# Retrieve a single todo by ID
@app.get("/todos/{todo_id}", response_model=Todo)
def get_todo(todo_id: str):
    todo = get_todo_by_id(todo_id)
    if not todo:
        raise HTTPException(status_code=404, detail="Todo not found")
    return todo


# Update an existing todo
@app.put("/todos/{todo_id}", response_model=Todo)
def update_todo(todo_id: str, todo_data: TodoCreate):
    try:
        todo = update_todo_record(
            todo_id=todo_id,
            title=todo_data.title,
            description=todo_data.description,
            completed=todo_data.completed,
        )
    except KeyError:
        raise HTTPException(status_code=404, detail="Todo not found")
    return Todo(**todo)


# Delete a todo
@app.delete("/todos/{todo_id}")
def delete_todo(todo_id: str):
    try:
        delete_todo_record(todo_id)
    except KeyError:
        raise HTTPException(status_code=404, detail="Todo not found")
    return {"detail": "Todo deleted successfully"}


# ErrorResponse model for custom error responses
class ErrorResponse(BaseModel):
    detail: str


# Adding custom error response for not found errors
@app.exception_handler(HTTPException)
def http_exception_handler(request, exc: HTTPException):
    return JSONResponse(
        status_code=exc.status_code,
        content={"detail": exc.detail},
    )


if __name__ == "__main__":
    import uvicorn

    uvicorn.run(app, host="0.0.0.0", port=8000)
