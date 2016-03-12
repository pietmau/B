package view.checkout;

import android.widget.AdapterView;

import model.api.Currency;
import model.api.Exchangerate;

/**
 * Created by Maurizio Pietrantuono, maurizio.pietrantuono@gmail.com.
 */
@SuppressWarnings("unused")
interface Checkout extends AdapterView.OnItemSelectedListener {
    void onCheckoutRequested();

    void onBasketChangeRequested();

    void onRatesAvailable(Exchangerate.Rates rates);

    void onRatesUnavailable(boolean notifyUser);

    void onCurrencySelected(Currency currency);
}
