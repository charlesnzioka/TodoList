package todolist.wfp.com.todolist.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import todolist.wfp.com.todolist.TodoListApp;
import todolist.wfp.com.todolist.di.builder.ActivityBuilder;
import todolist.wfp.com.todolist.di.module.AppModule;

/**
 * Created by charles on 07/07/17.
 */
@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent {

    void inject(TodoListApp app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
