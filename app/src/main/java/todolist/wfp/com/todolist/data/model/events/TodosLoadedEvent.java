package todolist.wfp.com.todolist.data.model.events;

import java.util.List;

import todolist.wfp.com.todolist.data.model.api.Todo;

public class TodosLoadedEvent {
    List<Todo> mTodos;

    public TodosLoadedEvent(List<Todo> todos) {
        mTodos = todos;
    }

    public List<Todo> getmTodos() {
        return mTodos;
    }

    public void setmTodos(List<Todo> mTodos) {
        this.mTodos = mTodos;
    }
}
