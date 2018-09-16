package todolist.wfp.com.todolist.ui.signin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import java.util.Map;

import javax.inject.Inject;

import todolist.wfp.com.todolist.BR;
import todolist.wfp.com.todolist.R;
import todolist.wfp.com.todolist.databinding.ActivitySignInBinding;
import todolist.wfp.com.todolist.ui.base.BaseActivity;
import todolist.wfp.com.todolist.ui.todo.TodoActivity;


public class SignInActivity extends BaseActivity<ActivitySignInBinding, SignInViewModel> implements SignInNavigator {

    @Inject
    SignInViewModel mSignInViewModel;
    private ActivitySignInBinding mActivitySignInBinding;

    public static Intent newIntent(Context context) {
        return new Intent(context, SignInActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivitySignInBinding = getViewDataBinding();
        mSignInViewModel.setNavigator(this);
    }

    @Override
    public void onStart() {
        super.onStart();

        // Check auth on Activity start
        mSignInViewModel.decideTheNextActivity();
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

    @Override
    public void openTodoListActivity() {
        startActivity(new Intent(SignInActivity.this, TodoActivity.class));
        finish();
    }

    @Override
    public void signIn() {
        String userName = mActivitySignInBinding.etUserName.getText().toString();
        String password = mActivitySignInBinding.etPassword.getText().toString();
        hideKeyboard();
        Map<Integer, String> validationResults = mSignInViewModel.validateForm(userName, mActivitySignInBinding.etUserName.getId(), password, mActivitySignInBinding.etPassword.getId());
        if (validationResults.isEmpty()) {
            mSignInViewModel.signInUser(userName, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            //hideProgressDialog();

                            if (task.isSuccessful()) {
                                mSignInViewModel.onAuthSuccess(task.getResult().getUser());
                            } else {
                                Toast.makeText(SignInActivity.this, "Sign In Failed",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            if (validationResults.containsKey(mActivitySignInBinding.etUserName.getId())) {
                mActivitySignInBinding.etUserName.setError("Invalid email address");
            }
            if (validationResults.containsKey(mActivitySignInBinding.etPassword.getId())) {
                mActivitySignInBinding.etPassword.setError("Password field is required");
            }
        }
    }

    @Override
    public void signUp() {
        String userName = mActivitySignInBinding.etUserName.getText().toString();
        String password = mActivitySignInBinding.etPassword.getText().toString();
        hideKeyboard();
        Map<Integer, String> validationResults = mSignInViewModel.validateForm(userName, mActivitySignInBinding.etUserName.getId(), password, mActivitySignInBinding.etPassword.getId());
        if (validationResults.isEmpty()) {
            mSignInViewModel.signUpUser(userName, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            //hideProgressDialog();

                            if (task.isSuccessful()) {
                                mSignInViewModel.onAuthSuccess(task.getResult().getUser());
                            } else {
                                Toast.makeText(SignInActivity.this, "Sign Up Failed",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            if (validationResults.containsKey(mActivitySignInBinding.etUserName.getId())) {
                mActivitySignInBinding.etUserName.setError("Invalid email address");
            }
            if (validationResults.containsKey(mActivitySignInBinding.etPassword.getId())) {
                mActivitySignInBinding.etPassword.setError("Password field is required");
            }
        }
    }
}