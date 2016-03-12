package model.api;

import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Maurizio Pietrantuono, maurizio.pietrantuono@gmail.com.
 */
public interface Api {
    Response<Exchangerate> getRatesSync();

    void getRatesAsync(Callback<Exchangerate> callback);
}
