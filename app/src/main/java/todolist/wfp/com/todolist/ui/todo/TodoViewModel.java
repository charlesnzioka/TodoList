package todolist.wfp.com.todolist.ui.todo;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import todolist.wfp.com.todolist.data.DataManager;
import todolist.wfp.com.todolist.data.model.api.Todo;
import todolist.wfp.com.todolist.ui.base.BaseViewModel;

public class TodoViewModel extends BaseViewModel<TodoActivity> {
    public final ObservableList<Todo> todoObservableArrayList = new ObservableArrayList<>();

    public TodoViewModel(DataManager dataManager) {
        super(dataManager);
    }

    public ObservableList<Todo> getTodoObservableArrayList() {
        return todoObservableArrayList;
    }

    public void onFabAddTodoClick() {
        this.getNavigator().addNewTodo();
    }
}
