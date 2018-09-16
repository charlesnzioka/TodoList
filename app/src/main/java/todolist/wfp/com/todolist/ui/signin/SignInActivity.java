package todolist.wfp.com.todolist.ui.signin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import todolist.wfp.com.todolist.R;
import todolist.wfp.com.todolist.BR;

import javax.inject.Inject;

import todolist.wfp.com.todolist.databinding.ActivitySignInBinding;
import todolist.wfp.com.todolist.ui.base.BaseActivity;


public class SignInActivity extends BaseActivity<ActivitySignInBinding, SignInViewModel> implements SignInNavigator {

    @Inject
    SignInViewModel mSignInViewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, SignInActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSignInViewModel.setNavigator(this);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_sign_in;
    }

    @Override
    public SignInViewModel getViewModel() {
        return mSignInViewModel;
    }

}