package application;

import android.app.Application;

import javax.inject.Inject;

import model.api.dagger.ApiComponent;
import model.api.dagger.ApiModule;
import model.api.dagger.DaggerApiComponent;
import presenter.PresenterImpl;

/**
 * Created by Maurizio Pietrantuono, maurizio.pietrantuono@gmail.com.
 */
public class App extends Application {

    @SuppressWarnings("unused")
    @Inject
    public static ApiComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerApiComponent.builder().apiModule(new ApiModule()).build().inject(this);
    }

    public static void inject(PresenterImpl presenter) {
        injector.inject(presenter);
    }
}
