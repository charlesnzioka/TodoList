package todolist.wfp.com.todolist.data.remote;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import javax.inject.Inject;
import javax.inject.Singleton;

import todolist.wfp.com.todolist.data.model.api.User;


@Singleton
public class AppApiHelper implements ApiHelper {

    private DatabaseReference mFirebaseDatabase;

    private FirebaseAuth mFirebaseAuth;

    @Inject
    public AppApiHelper(DatabaseReference firebaseDatabase, FirebaseAuth firebaseAuth) {
        mFirebaseDatabase = firebaseDatabase;
        mFirebaseAuth = firebaseAuth;
    }

    @Override
    public FirebaseUser getCurrentUser() {
        return mFirebaseAuth.getCurrentUser();
    }

    @Override
    public void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email);

        mFirebaseDatabase.child("users").child(userId).setValue(user);
    }

    @Override
    public Task<AuthResult> signInUser(String userName, String password) {
        return mFirebaseAuth.signInWithEmailAndPassword(userName, password);
    }

    @Override
    public Task<AuthResult> signUpUser(String userName, String password) {
        return mFirebaseAuth.createUserWithEmailAndPassword(userName, password);
    }
}
