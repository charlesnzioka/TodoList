
package todolist.wfp.com.todolist.ui.todo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import todolist.wfp.com.todolist.data.model.api.Todo;
import todolist.wfp.com.todolist.databinding.ItemTodoEmptyViewBinding;
import todolist.wfp.com.todolist.databinding.ItemTodoViewBinding;
import todolist.wfp.com.todolist.ui.base.BaseViewHolder;

public class TodoAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;

    public static final int VIEW_TYPE_NORMAL = 1;

    private List<Todo> mTodoResponseList;

    private TodoAdapterListener mListener;


    public TodoAdapter(List<Todo> todoResponseList) {
        this.mTodoResponseList = todoResponseList;
    }

    public void setmTodoResponseList(List<Todo> mTodoResponseList) {
        this.mTodoResponseList = mTodoResponseList;
    }

    @Override
    public int getItemCount() {
        if (mTodoResponseList != null && mTodoResponseList.size() > 0) {
            return mTodoResponseList.size();
        } else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mTodoResponseList != null && !mTodoResponseList.isEmpty()) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                ItemTodoViewBinding todoViewBinding = ItemTodoViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new TodoViewHolder(todoViewBinding);
            case VIEW_TYPE_EMPTY:
            default:
                ItemTodoEmptyViewBinding emptyViewBinding = ItemTodoEmptyViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new EmptyViewHolder(emptyViewBinding);
        }
    }

    public void addItems(List<Todo> todoList) {
        mTodoResponseList.addAll(todoList);
        this.notifyDataSetChanged();

    }

    public void clearItems() {
        mTodoResponseList.clear();
    }

    public void setListener(TodoAdapterListener listener) {
        this.mListener = listener;
    }

    public interface TodoAdapterListener {

        void onRetryClick();
    }

    public class TodoViewHolder extends BaseViewHolder implements TodoItemViewModel.TodoItemViewModelListener {

        private ItemTodoViewBinding mBinding;

        private TodoItemViewModel mTodoItemViewModel;

        public TodoViewHolder(ItemTodoViewBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final Todo todo = mTodoResponseList.get(position);
            mTodoItemViewModel = new TodoItemViewModel(todo, this);
            mBinding.setViewModel(mTodoItemViewModel);

            // Immediate Binding
            // When a variable or observable changes, the binding will be scheduled to change before
            // the next frame. There are times, however, when binding must be executed immediately.
            // To force execution, use the executePendingBindings() method.
            mBinding.executePendingBindings();
        }

        @Override
        public void onItemClick(String uid) {
            if (uid != null) {
                try {

                } catch (Exception e) {
                    //AppLogger.d("url error");
                }
            }
        }
    }

    public class EmptyViewHolder extends BaseViewHolder implements TodoEmptyItemViewModel.TodoEmptyItemViewModelListener {

        private ItemTodoEmptyViewBinding mBinding;

        public EmptyViewHolder(ItemTodoEmptyViewBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            TodoEmptyItemViewModel emptyItemViewModel = new TodoEmptyItemViewModel(this);
            mBinding.setViewModel(emptyItemViewModel);
        }

        @Override
        public void onRetryClick() {
            mListener.onRetryClick();
        }
    }
}