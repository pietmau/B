package api.mocks;

import model.api.Api;
import model.api.Exchangerate;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Maurizio Pietrantuono, maurizio.pietrantuono@gmail.com.
 */
public class ApiMockError implements Api {

    @Override
    public Response<Exchangerate> getRatesSync() {
        return null;
    }

    @Override
    public void getRatesAsync(Callback<Exchangerate> callback) {
        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException ignored) {
        }
        callback.onFailure(null, null);
    }
}
