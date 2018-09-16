package todolist.wfp.com.todolist.ui.signin;

import dagger.Module;
import dagger.Provides;
import todolist.wfp.com.todolist.data.DataManager;

@Module
public class SignInActivityModule {
    @Provides
    SignInViewModel provideSignInViewModel(DataManager dataManager) {
        return new SignInViewModel(dataManager);
    }
}
