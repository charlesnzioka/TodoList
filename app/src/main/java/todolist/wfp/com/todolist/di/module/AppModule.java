
package todolist.wfp.com.todolist.di.module;

import android.app.Application;
import android.content.Context;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import todolist.wfp.com.todolist.R;
import todolist.wfp.com.todolist.data.AppDataManager;
import todolist.wfp.com.todolist.data.DataManager;
import todolist.wfp.com.todolist.data.remote.ApiHelper;
import todolist.wfp.com.todolist.data.remote.AppApiHelper;
import todolist.wfp.com.todolist.di.ApiInfo;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

@Module
public class AppModule {

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }



    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

}
