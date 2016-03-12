package model.api.dagger;

import javax.inject.Singleton;

import application.App;
import dagger.Component;
import model.api.Api;
import presenter.PresenterImpl;

/**
 * Created by Maurizio Pietrantuono, maurizio.pietrantuono@gmail.com.
 */
@SuppressWarnings("unused")
@Component(modules = ApiModule.class )
@Singleton
public interface ApiComponent {

    Api provideApi();

    void inject(PresenterImpl presenter);

    void inject(App app);
}
