package todolist.wfp.com.todolist.ui.signin;

import android.text.TextUtils;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;
import java.util.Map;

import todolist.wfp.com.todolist.data.DataManager;
import todolist.wfp.com.todolist.ui.base.BaseViewModel;
import todolist.wfp.com.todolist.utils.CommonUtils;

import static todolist.wfp.com.todolist.utils.CommonUtils.usernameFromEmail;


public class SignInViewModel extends BaseViewModel<SignInActivity> {

    public SignInViewModel(DataManager dataManager) {
        super(dataManager);
    }

    public void onServerLoginClick() {
        getNavigator().signIn();
    }

    public void onServerSignUpClick(){
        getNavigator().signUp();
    }

    public void decideTheNextActivity() {
        // Check auth on Activity start
        if (getDataManager().getCurrentUser() != null) {
            onAuthSuccess(getDataManager().getCurrentUser());
        }
    }

    public void onAuthSuccess(FirebaseUser user) {
        String username = usernameFromEmail(user.getEmail());

        // Write new user
        getDataManager().writeNewUser(user.getUid(), username, user.getEmail());

        // Go to TodoListActivity
        getNavigator().openTodoListActivity();
    }

    public Map<Integer, String> validateForm(String userName, int userNameId, String password, int passwordId) {
        Map<Integer, String> result = new HashMap<>();
        if (!CommonUtils.isEmailValid(userName)) {
            result.put(userNameId, "Invalid email address");
        }

        if (TextUtils.isEmpty(password)) {
            result.put(passwordId, "Password field required");
        }

        return result;
    }

    public Task<AuthResult> signInUser(String userName, String password) {
        return getDataManager().signInUser(userName, password);
    }

    public Task<AuthResult> signUpUser(String userName, String password) {
        return getDataManager().signUpUser(userName, password);
    }

}