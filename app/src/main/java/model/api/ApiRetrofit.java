package model.api;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by Maurizio Pietrantuono, maurizio.pietrantuono@gmail.com.
 */
public class ApiRetrofit implements Api {
    private final GetRatesService getRatesService;
    private static final String URL = "http://api.fixer.io/";

    public interface GetRatesService {
        @GET("latest?base=GBP")
        Call<Exchangerate> getRates();
    }

    @SuppressWarnings("unused")
    public ApiRetrofit() {
        // Api sometimes takes longer to respond, for simplicity we set a longer timeout
        // And we make sure the client retries if there is a connection problem
        OkHttpClient okHttpClient=new OkHttpClient.Builder().
                readTimeout(60, TimeUnit.SECONDS).
                connectTimeout(60,TimeUnit.SECONDS).
                retryOnConnectionFailure(true).
                build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        getRatesService = retrofit.create(GetRatesService.class);
    }

    @Override
    public Response<Exchangerate> getRatesSync() {
        Call<Exchangerate> call = getRatesService.getRates();
        try {
            return call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void getRatesAsync(Callback<Exchangerate> callback) {
        Call<Exchangerate> call = getRatesService.getRates();
        call.enqueue(callback);
    }
}
