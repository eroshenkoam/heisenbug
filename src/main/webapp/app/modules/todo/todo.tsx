import React, { useEffect } from 'react';

import { Row, Col, Alert, Button } from 'reactstrap';
import { ValidatedField, ValidatedForm } from 'react-jhipster';
import {reset, todoCreate, todoDelete, todoFindAllAction} from "app/modules/todo/todo.reducer";
import {ITodo} from "app/shared/model/todo.model";
import {useAppDispatch, useAppSelector} from "app/config/store";

export const Todo = () => {
  const dispatch = useAppDispatch();
  const todos = useAppSelector(state => state.todo.todos || []);

  useEffect(() => {
    dispatch(todoFindAllAction());
    return () => {
      dispatch(reset());
    };
  }, [])

  const handleCreateSubmit = (value: ITodo) => {
    dispatch(todoCreate(value)).then(() => dispatch(todoFindAllAction()));
  };

  const handleDelete = (value: ITodo) => {
    dispatch(todoDelete(value.id)).then(() => dispatch(todoFindAllAction()));
  };

  return (
    <Row className="justify-content-center">
      <Col md="4">
        <ValidatedForm id="password-form" onSubmit={handleCreateSubmit}>
          <ValidatedField
            name="title"
            label="Title"
            placeholder={'Todo title'}
            type="text"
            validate={{
              required: { value: true, message: 'Todo title is required.' },
            }}
            data-cy="todoTitleInput"
          />
          <Button color="success" type="submit" data-cy="todoCreate">
            Create
          </Button>
        </ValidatedForm>
      </Col>
      <Col md="4">
        {todos.map(todo => (
          <div key={todo.id}>
            <Alert>{todo.title}</Alert>
          </div>
        ))}
      </Col>
    </Row>
  );
};

export default Todo;
