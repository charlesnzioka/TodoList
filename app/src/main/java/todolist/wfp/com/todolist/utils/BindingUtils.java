
package todolist.wfp.com.todolist.utils;

import android.databinding.BindingAdapter;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import todolist.wfp.com.todolist.data.model.api.Todo;
import todolist.wfp.com.todolist.ui.todo.TodoAdapter;

public final class BindingUtils {

    private BindingUtils() {
        // This class is not publicly instantiable
    }

    @BindingAdapter({"adapter"})
    public static void addTodoItems(RecyclerView recyclerView, List<Todo> todos) {
        TodoAdapter adapter = (TodoAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(todos);
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    adapter.notifyItemRangeChanged(0, todos.size());
                }
            });
        }
    }



}



