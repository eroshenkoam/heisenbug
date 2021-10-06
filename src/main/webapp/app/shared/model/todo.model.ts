export interface ITodo {
  id?: any;
  title?: string;
}

export const defaultValue: Readonly<ITodo> = {
  id: '',
  title: '',
};
