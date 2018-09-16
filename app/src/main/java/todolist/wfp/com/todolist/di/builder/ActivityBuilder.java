
package todolist.wfp.com.todolist.di.builder;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import todolist.wfp.com.todolist.ui.signin.SignInActivity;
import todolist.wfp.com.todolist.ui.signin.SignInActivityModule;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {
            SignInActivityModule.class})
    abstract SignInActivity bindSignInActivity();

}
