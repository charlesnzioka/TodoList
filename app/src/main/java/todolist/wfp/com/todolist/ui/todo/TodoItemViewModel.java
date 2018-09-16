
package todolist.wfp.com.todolist.ui.todo;

import android.databinding.ObservableField;

import todolist.wfp.com.todolist.data.model.api.Todo;

;

public class TodoItemViewModel {

    public final ObservableField<String> uid;

    public final ObservableField<String> name;

    public final ObservableField<String> postDate;


    public final TodoItemViewModelListener mListener;

    private final Todo mTodo;

    public TodoItemViewModel(Todo todo, TodoItemViewModelListener listener) {
        this.mTodo = todo;
        this.mListener = listener;
        name = new ObservableField<>(mTodo.name);
        postDate = new ObservableField<>(mTodo.postDate);
        uid = new ObservableField<>(mTodo.uid);
    }

    public void onItemClick() {
        mListener.onItemClick(String.valueOf(uid));
    }

    public interface TodoItemViewModelListener {

        void onItemClick(String carId);
    }


}
