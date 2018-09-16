package todolist.wfp.com.todolist;

import android.app.Activity;
import android.support.multidex.MultiDexApplication;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import todolist.wfp.com.todolist.di.component.DaggerAppComponent;
import todolist.wfp.com.todolist.utils.AppLogger;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class TodoListApp extends MultiDexApplication implements HasActivityInjector {
    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    @Inject
    CalligraphyConfig mCalligraphyConfig;


    @Override
    public void onCreate() {
        super.onCreate();

        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);

        AppLogger.init();

        CalligraphyConfig.initDefault(mCalligraphyConfig);
    }
}
