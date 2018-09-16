package todolist.wfp.com.todolist.ui.todo;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import todolist.wfp.com.todolist.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class TodoActivityFragment extends Fragment {

    public TodoActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_todo, container, false);
    }
}
