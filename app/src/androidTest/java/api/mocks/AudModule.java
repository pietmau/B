package api.mocks;

import model.api.dagger.ApiModule;
import model.api.Api;

/**
 * Created by Maurizio Pietrantuono, maurizio.pietrantuono@gmail.com.
 */
public class AudModule extends ApiModule {

    @Override
    public Api providesApi() {
            return new ApiMockResponseAUD();
    }
}
