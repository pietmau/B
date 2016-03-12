package api.mocks;

import model.api.Api;
import model.api.dagger.ApiModule;

/**
 * Created by Maurizio Pietrantuono, maurizio.pietrantuono@gmail.com.
 */
public class ErrorModule extends ApiModule {

    @Override
    public Api providesApi() {
            return new ApiMockError();
    }
}
