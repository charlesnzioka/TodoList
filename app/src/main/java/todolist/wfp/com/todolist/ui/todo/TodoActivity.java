package todolist.wfp.com.todolist.ui.todo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import todolist.wfp.com.todolist.BR;
import todolist.wfp.com.todolist.R;
import todolist.wfp.com.todolist.data.model.events.TodosLoadedEvent;
import todolist.wfp.com.todolist.databinding.ActivityTodoBinding;
import todolist.wfp.com.todolist.ui.base.BaseActivity;

public class TodoActivity extends BaseActivity<ActivityTodoBinding, TodoViewModel> implements TodoNavigator {

    AlertDialog addNewTodoDialog = null;
    @Inject
    TodoAdapter mTodoAdapter;

    @Inject
    TodoViewModel mTodoViewModel;


    @Inject
    LinearLayoutManager mLayoutManager;

    @Inject
    Bus mBus;


    private ActivityTodoBinding mActivityTodoBinding;

    public static Intent newIntent(Context context) {
        return new Intent(context, TodoActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityTodoBinding = getViewDataBinding();
        mTodoViewModel.setNavigator(this);
        mBus.register(this);
        setUp();
        subscribeToLiveData();
        mTodoViewModel.fetchAllTodos();
    }

    @Subscribe
    public void updateTodos(TodosLoadedEvent todosLoadedEvent) {
        mTodoViewModel.addTodoItemsToList(todosLoadedEvent.getmTodos());
    }


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_todo;
    }

    @Override
    public TodoViewModel getViewModel() {
        return mTodoViewModel;
    }


    private void setUp() {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mActivityTodoBinding.alarmRecyclerView.setLayoutManager(mLayoutManager);
        mActivityTodoBinding.alarmRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mActivityTodoBinding.alarmRecyclerView.setAdapter(mTodoAdapter);
    }

    @Override
    public void addNewTodo() {
        String toDoItemText = mActivityTodoBinding.newTodoItem.getText().toString();
        if (toDoItemText != null && toDoItemText.length() > 6) {
            mTodoViewModel.addNewTodoItem(toDoItemText);
            mActivityTodoBinding.newTodoItem.setText("");
        } else {
            Toast.makeText(this, "Please input a valid Todo item text", Toast.LENGTH_SHORT).show();
        }
    }

    private void subscribeToLiveData() {
        mTodoViewModel.getTodoListLiveData().observe(this, todos -> mTodoViewModel.addTodoItemsToList(todos));
    }
}
