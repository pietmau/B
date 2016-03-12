package api.mocks;

import model.api.Api;
import model.api.Exchangerate;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Maurizio Pietrantuono, maurizio.pietrantuono@gmail.com.
 */
public class ApiMockResponseAUD implements Api {

    @Override
    public Response<Exchangerate> getRatesSync() {
        return null;
    }

    @Override
    public void getRatesAsync(Callback<Exchangerate> callback) {
        Exchangerate exchangerate = new Exchangerate();
        exchangerate.setBase("test");
        exchangerate.setDate("test");
        Exchangerate.Rates rates = new Exchangerate.Rates();
        rates.setAUD("1.1");
        rates.setBGN("1.1");
        exchangerate.setRates(rates);
        Response<Exchangerate> response=Response.success(exchangerate);
        callback.onResponse(null,response);
    }
}
