
package todolist.wfp.com.todolist.ui.todo;

public class TodoEmptyItemViewModel {

    private TodoEmptyItemViewModelListener mListener;

    public TodoEmptyItemViewModel(TodoEmptyItemViewModelListener listener) {
        this.mListener = listener;
    }

    public void onRetryClick() {
        mListener.onRetryClick();
    }

    public interface TodoEmptyItemViewModelListener {

        void onRetryClick();
    }
}
