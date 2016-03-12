package presenter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.AdapterView;

import com.pietrantuono.view.R;

import java.lang.reflect.Field;

import javax.inject.Inject;

import model.api.Api;
import model.api.Exchangerate;
import model.api.Currency;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.checkout.CheckoutView;
import view.navigation.NavigationView;

/**
 * Created by Maurizio Pietrantuono, maurizio.pietrantuono@gmail.com.
 */
public class PresenterImpl extends Fragment implements Presenter {
    private static final String TAG = "Presenter";
    private static final int INVALID_POSITION = -1;
    private CheckoutView checkoutView;
    private Exchangerate.Rates rates;
    private Currency rate;
    private int spinnerPosition = INVALID_POSITION;
    private boolean ratesUnavailabe;
    private boolean unsernotified;
    @SuppressWarnings({"WeakerAccess", "unused"})
    @Inject
    public Api api;

    public static PresenterImpl getInstance(Fragment fragment) {
        FragmentManager manager = fragment.getFragmentManager();
        PresenterImpl instance = (PresenterImpl) manager.findFragmentByTag(TAG);
        if (instance == null) {
            instance = new PresenterImpl();
            manager.beginTransaction().add(instance, TAG).commit();
        }
        return instance;
    }

    public PresenterImpl() {
    }

    @Override
    public void setView(CheckoutView view) {
        this.checkoutView = view;
        if (view == null) return;
        // If data (that we retain across config change because we are a retained frag)
        // are present we set them immediately in the view
        if (!ratesUnavailabe) {
            if (rates != null) checkoutView.onRatesAvailable(rates);
            if (rate != null) checkoutView.onCurrencySelected(rate);
            if (spinnerPosition != INVALID_POSITION)
                checkoutView.setSpinnerPosition(spinnerPosition);
        } else onRatesUnavailable();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        // As soon as we are created we go and try to get the data
        api.getRatesAsync(new Callback<Exchangerate>() {
            @Override
            public void onResponse(Call<Exchangerate> call, Response<Exchangerate> response) {
                onRatesAvailable(response);
            }

            @Override
            public void onFailure(Call<Exchangerate> call, Throwable t) {
                onRatesUnavailable();
            }
        });
    }

    /**
     * Sets the rates in the view
     */
    @Override
    public void onRatesAvailable(Response<Exchangerate> response) {
        ratesUnavailabe = false;
        if (response == null || response.body() == null || response.body().getRates() == null)
            return;
        Exchangerate.Rates rates = response.body().getRates();
        if (rates.getClass().getFields().length <= 0) return;
        this.rates = rates;
        if (checkoutView == null) return;
        // If the view is there we set the data
        checkoutView.onRatesAvailable(rates);

    }

    /**
     * Gets the rate corresponding to the position in the spinner
     * and sets it back to the view
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position == spinnerPosition) return;
        this.spinnerPosition = position;
        // We get the rate
        rate = getRate(position);
        // We set the rate in the view
        checkoutView.onCurrencySelected(rate);
    }

    /**
     * Gets the rate corresponding to the position form the Rates object
     */
    private Currency getRate(int position) {
        if (position == 0) return null;
        else if (position == 1) {
            return new Currency(getResources().getString(R.string.gbp), "1");
        } else if (position >= 2) {
            Field field = rates.getClass().getFields()[position - 2];
            String symbol = field.getName();
            String value = null;
            try {
                value = (String) field.get(rates);
            } catch (Exception ignored) {
            }
            return new Currency(symbol, value);
        }
        return null;
    }

    @Override
    public void onBasketChangeRequested() {
        ((NavigationView) getActivity()).goBackToBasket();
    }

    @Override
    public void onCheckoutRequested() {
        ((NavigationView) getActivity()).goToBasket();
    }

    @Override
    public void onRatesUnavailable() {
        ratesUnavailabe = true;
        if (checkoutView != null) {
            checkoutView.onRatesUnavailable(!unsernotified);
            unsernotified=true;
        }
    }
}
