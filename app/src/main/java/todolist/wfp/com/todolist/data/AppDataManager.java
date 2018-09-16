package todolist.wfp.com.todolist.data;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import javax.inject.Inject;

import todolist.wfp.com.todolist.data.model.api.Todo;
import todolist.wfp.com.todolist.data.remote.ApiHelper;

public class AppDataManager implements DataManager {
    private final ApiHelper mApiHelper;

    @Inject
    public AppDataManager(ApiHelper apiHelper) {
        mApiHelper = apiHelper;
    }

    @Override
    public FirebaseUser getCurrentUser() {
        return mApiHelper.getCurrentUser();
    }

    @Override
    public void writeNewUser(String userId, String name, String email) {
        mApiHelper.writeNewUser(userId, name, email);
    }

    @Override
    public Task<AuthResult> signInUser(String userName, String password) {
        return mApiHelper.signInUser(userName, password);
    }

    @Override
    public Task<AuthResult> signUpUser(String userName, String password) {
        return mApiHelper.signUpUser(userName, password);
    }

    @Override
    public void addNewTodoItem(String todoItemText) {
        mApiHelper.addNewTodoItem(todoItemText);
    }

    @Override
    public List<Todo> fetchAllTodos() {
        return mApiHelper.fetchAllTodos();
    }
}
