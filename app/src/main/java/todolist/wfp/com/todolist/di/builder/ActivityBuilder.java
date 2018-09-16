
package todolist.wfp.com.todolist.di.builder;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import todolist.wfp.com.todolist.ui.signin.SignInActivity;
import todolist.wfp.com.todolist.ui.signin.SignInActivityModule;
import todolist.wfp.com.todolist.ui.todo.TodoActivity;
import todolist.wfp.com.todolist.ui.todo.TodoActivityModule;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {
            SignInActivityModule.class})
    abstract SignInActivity bindSignInActivity();

    @ContributesAndroidInjector(modules = {
            TodoActivityModule.class})
    abstract TodoActivity bindTodoActivity();

}
