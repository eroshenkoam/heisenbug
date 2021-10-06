import axios from 'axios';
import { createAsyncThunk, createSlice } from '@reduxjs/toolkit';

import { serializeAxiosError } from 'app/shared/reducers/reducer.utils';
import { ITodo } from 'app/shared/model/todo.model';

const initialState: { todos?: ITodo[] } = {
  todos: undefined,
};

export type TodoState = Readonly<typeof initialState>;

// Actions

export const todoFindAllAction = createAsyncThunk('todo/findall', async () => axios.get<ITodo[]>('api/todo'), {
  serializeError: serializeAxiosError,
});

export const todoCreate = createAsyncThunk('todo/create', async (data: ITodo) => axios.post<ITodo, ITodo>('api/todo', data), {
  serializeError: serializeAxiosError,
});

export const todoDelete = createAsyncThunk('todo/delete', async (id: number) => axios.delete('api/todo/${id}'), {
  serializeError: serializeAxiosError,
});

export const TodoSlice = createSlice({
  name: 'todo',
  initialState: initialState as TodoState,
  reducers: {
    reset() {
      return initialState;
    },
  },
  extraReducers(builder) {
    builder
      .addCase(todoFindAllAction.pending, () => initialState)
      .addCase(todoFindAllAction.rejected, state => {
        state.todos = undefined;
      })
      .addCase(todoFindAllAction.fulfilled, (state, action) => {
        state.todos = action.payload.data;
      })
      .addCase(todoCreate.pending, state => state)
      .addCase(todoCreate.rejected, state => state)
      .addCase(todoCreate.fulfilled, state => state);
  },
});

export const { reset } = TodoSlice.actions;

// Reducer
export default TodoSlice.reducer;
