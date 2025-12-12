import copy
import uuid
from datetime import datetime

import pytest
from hypothesis import assume, given, strategies as st

import service


@pytest.fixture(autouse=True)
def reset_todos():
    snapshot = copy.deepcopy(service.todos)
    yield
    service.todos.clear()
    service.todos.extend(snapshot)


text_strategy = st.text(min_size=1, max_size=50)
description_strategy = st.one_of(st.none(), st.text(max_size=100))


@given(title=text_strategy, description=description_strategy, completed=st.booleans())
def test_create_todo_preserves_input_and_generates_uuid(title, description, completed):
    before_len = len(service.todos)
    todo = service.create_todo_record(title=title, description=description, completed=completed)
    assert len(service.todos) == before_len + 1
    assert uuid.UUID(todo["id"])
    assert todo["title"] == title
    assert todo["description"] == description
    assert todo["completed"] == completed
    assert isinstance(todo["created_at"], datetime)


@given(new_title=text_strategy, new_description=description_strategy, new_completed=st.booleans())
def test_update_todo_changes_fields_and_preserves_id(new_title, new_description, new_completed):
    base = service.create_todo_record("base", "desc", False)
    updated = service.update_todo_record(
        todo_id=base["id"],
        title=new_title,
        description=new_description,
        completed=new_completed,
    )
    assert updated["id"] == base["id"]
    assert updated["title"] == new_title
    assert updated["description"] == new_description
    assert updated["completed"] == new_completed
    assert service.find_todo(base["id"]) == updated


@given(title=text_strategy, description=description_strategy, completed=st.booleans())
def test_delete_todo_removes_item(title, description, completed):
    todo = service.create_todo_record(title=title, description=description, completed=completed)
    service.delete_todo_record(todo["id"])
    assert service.find_todo(todo["id"]) is None
    assert all(entry["id"] != todo["id"] for entry in service.todos)


@given(random_id=st.uuids())
def test_update_unknown_id_raises(random_id):
    assume(str(random_id) not in {entry["id"] for entry in service.todos})
    with pytest.raises(KeyError):
        service.update_todo_record(
            todo_id=str(random_id),
            title="missing",
            description=None,
            completed=False,
        )


@given(title=text_strategy, description=description_strategy, completed=st.booleans())
def test_list_todos_contains_required_keys(title, description, completed):
    service.create_todo_record(title=title, description=description, completed=completed)
    for todo in service.list_todos():
        assert {"id", "title", "description", "completed", "created_at"} <= todo.keys()


@given(first=text_strategy, second=text_strategy)
def test_new_ids_are_unique(first, second):
    todo_a = service.create_todo_record(title=first, description=None, completed=False)
    todo_b = service.create_todo_record(title=second, description=None, completed=False)
    assert todo_a["id"] != todo_b["id"]
