package todolist.wfp.com.todolist.data.model.api;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Todo {
    public String uid;
    public String name;
    public String postDate;

    public Todo() {
        // Default constructor required for calls to DataSnapshot.getValue(Todo.class)
    }

    public Todo(String uid, String name, String postDate) {
        this.uid = uid;
        this.name = name;
        this.postDate = postDate;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("name", name);
        result.put("postDate", postDate);
        return result;
    }
}
