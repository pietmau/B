package model.api.dagger;

import javax.inject.Singleton;

import dagger.Provides;
import model.api.Api;
import model.api.ApiRetrofit;

/**
 * Created by Maurizio Pietrantuono, maurizio.pietrantuono@gmail.com.
 */
@SuppressWarnings("unused")
@dagger.Module
public class ApiModule {

    @Provides
    @Singleton
    public Api providesApi(){
        return new ApiRetrofit() ;
    }
}
