package todolist.wfp.com.todolist.data.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import todolist.wfp.com.todolist.data.model.api.Todo;
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

    @Override
    public void addNewTodoItem(String todoItemText) {
        String uid = mFirebaseDatabase.child("todos").push().getKey();
        Todo todo = new Todo(uid, todoItemText, new Date().toString());
        mFirebaseDatabase.child("todos").child(todo.uid).setValue(todo).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Jinga", e.getLocalizedMessage());
            }
        });
    }

    @Override
    public List<Todo> fetchAllTodos() {
        List<Todo> result = new ArrayList<>();
        mFirebaseDatabase.child("todos").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            Todo todo = data.getValue(Todo.class);
                            result.add(todo);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("TodoApp", "getUser:onCancelled", databaseError.toException());
                    }
                });
        return result;
    }
}
