package todolist.wfp.com.todolist.data.remote;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public interface ApiHelper {
    FirebaseUser getCurrentUser();

    void writeNewUser(String userId, String name, String email);

    Task<AuthResult> signInUser(String userName, String password);

    Task<AuthResult> signUpUser(String userName, String password);
}
