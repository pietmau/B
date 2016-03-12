package presenter;

import android.view.View;
import android.widget.AdapterView;

import model.api.Exchangerate;
import retrofit2.Response;
import view.checkout.CheckoutView;

/**
 * Created by Maurizio Pietrantuono, maurizio.pietrantuono@gmail.com.
 */
@SuppressWarnings("unused")
interface Presenter {
    void setView(CheckoutView view);

    void onRatesAvailable(Response<Exchangerate> response);

    void onItemSelected(AdapterView<?> parent, View view, int position, long id);

    void onBasketChangeRequested();

    void onCheckoutRequested();

    void onRatesUnavailable();
}
