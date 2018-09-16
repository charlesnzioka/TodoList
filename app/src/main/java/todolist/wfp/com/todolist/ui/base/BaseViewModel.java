
package todolist.wfp.com.todolist.ui.base;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;

import todolist.wfp.com.todolist.data.DataManager;


public abstract class BaseViewModel<N> extends ViewModel {

    private final DataManager mDataManager;

    private final ObservableBoolean mIsLoading = new ObservableBoolean(false);


    private N mNavigator;

    public BaseViewModel(DataManager dataManager) {
        this.mDataManager = dataManager;

    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }


    public DataManager getDataManager() {
        return mDataManager;
    }

    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }

    public void setIsLoading(boolean isLoading) {
        mIsLoading.set(isLoading);
    }

    public N getNavigator() {
        return mNavigator;
    }

    public void setNavigator(N navigator) {
        this.mNavigator = navigator;
    }

}
