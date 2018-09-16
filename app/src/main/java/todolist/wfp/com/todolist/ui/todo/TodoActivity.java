package todolist.wfp.com.todolist.ui.todo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import javax.inject.Inject;

import todolist.wfp.com.todolist.BR;
import todolist.wfp.com.todolist.R;
import todolist.wfp.com.todolist.databinding.ActivityTodoBinding;
import todolist.wfp.com.todolist.ui.base.BaseActivity;

public class TodoActivity extends BaseActivity<ActivityTodoBinding, TodoViewModel> implements TodoNavigator {
    @Inject
    TodoAdapter mTodoAdapter;

    @Inject
    TodoViewModel mTodoViewModel;


    @Inject
    LinearLayoutManager mLayoutManager;


    private ActivityTodoBinding mActivityTodoBinding;

    public static Intent newIntent(Context context) {
        return new Intent(context, TodoActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityTodoBinding = getViewDataBinding();
        mTodoViewModel.setNavigator(this);
        setUp();
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

}
