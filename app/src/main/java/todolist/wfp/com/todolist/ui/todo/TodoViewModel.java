package todolist.wfp.com.todolist.ui.todo;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import java.util.List;

import todolist.wfp.com.todolist.data.DataManager;
import todolist.wfp.com.todolist.data.model.api.Todo;
import todolist.wfp.com.todolist.ui.base.BaseViewModel;

public class TodoViewModel extends BaseViewModel<TodoActivity> {
    private final MutableLiveData<List<Todo>> todoListLiveData;

    public final ObservableList<Todo> todoObservableArrayList = new ObservableArrayList<>();

    public TodoViewModel(DataManager dataManager) {
        super(dataManager);
        todoListLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<Todo>> getTodoListLiveData() {
        return todoListLiveData;
    }

    public void addTodoItemsToList(List<Todo> todos) {
        todoObservableArrayList.clear();
        todoObservableArrayList.addAll(todos);

    }

    public ObservableList<Todo> getTodoObservableArrayList() {
        return todoObservableArrayList;
    }

    public void onFabAddTodoClick() {
        this.getNavigator().addNewTodo();
        fetchAllTodos();
    }

    public void fetchAllTodos() {
        getDataManager().fetchAllTodos();
    }

    public void addNewTodoItem(String todoItemText) {
        getDataManager().addNewTodoItem(todoItemText);
    }
}
