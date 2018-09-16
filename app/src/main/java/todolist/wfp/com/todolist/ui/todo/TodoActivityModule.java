package todolist.wfp.com.todolist.ui.todo;

import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import todolist.wfp.com.todolist.data.DataManager;

@Module
public class TodoActivityModule {
    @Provides
    TodoViewModel provideTodoViewModel(DataManager dataManager) {
        return new TodoViewModel(dataManager);
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(TodoActivity activity) {
        return new LinearLayoutManager(activity);
    }

    @Provides
    TodoAdapter provideTodoAdapter() {
        return new TodoAdapter(new ArrayList<>());
    }


}
