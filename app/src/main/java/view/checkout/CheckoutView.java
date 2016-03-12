package view.checkout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.pietrantuono.view.R;

import java.util.ArrayList;

import application.App;
import model.api.Currency;
import model.api.Exchangerate;
import model.classes.ShoppingItem;
import presenter.PresenterImpl;
import view.customviews.checkout.CheckoutRecyclerView;
import view.customviews.checkout.CheckoutSpinner;
import view.customviews.checkout.TotalView;


public class CheckoutView extends Fragment implements Checkout  {
    public static final String TAG = "CheckoutFragment";
    public static final String ITEMS = "items";
    private ArrayList<ShoppingItem> items;
    private PresenterImpl presenter;
    private View v;

    public CheckoutView() {
    }

    public static CheckoutView newInstance() {
        return new CheckoutView();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            items = getArguments().getParcelableArrayList(ITEMS);
        }
        // Here as presenter we use a retained fragment (it needs a fragment manager, hence we pass this)
        presenter = PresenterImpl.getInstance(CheckoutView.this);
        // We inject the network classes in the presenter
        // Please note: there are 2 implementations of the component in the 2 productFlavors
        //DaggerApiComponent.builder().apiModule(new ApiModule()).build().inject(presenter);
        App.inject(presenter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_checkout, container, false);
        // If we are starting new
        if (savedInstanceState == null) {
            // We use again a custom view to make code more clean, the view saves its state (the items)
            ((CheckoutRecyclerView) v.findViewById(R.id.checkout_recycler)).setItems(items);
            // Another custom view
            ((TotalView) v.findViewById(R.id.total_view)).setItems(items);
        }
        v.findViewById(R.id.change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBasketChangeRequested();
            }
        });
        v.findViewById(R.id.checkout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), R.string.thanks,Toast.LENGTH_SHORT).show();
                onCheckoutRequested();
            }
        });
        this.v = v;
        return v;
    }

    @Override
    public void onCheckoutRequested() {
        presenter.onCheckoutRequested();
    }

    @Override
    public void onBasketChangeRequested() {
        presenter.onBasketChangeRequested();
    }

    @Override
    public void onResume() {
        super.onResume();
        // Set the view in the presenter
        presenter.setView(CheckoutView.this);
    }

    @Override
    public void onPause() {
        super.onPause();
        // Remove the view from the presenter
        presenter.setView(null);
    }

    @Override
    public void onRatesAvailable(Exchangerate.Rates rates) {
        if (this.v == null) return;
        v.findViewById(R.id.progress).setVisibility(View.INVISIBLE);
        ((CheckoutSpinner) v.findViewById(R.id.spinner)).onRatesAvailable(rates, this);
    }

    @Override
    public void onRatesUnavailable(boolean notifyUser) {
        v.findViewById(R.id.progress).setVisibility(View.INVISIBLE);
        if(notifyUser)Toast.makeText(getActivity(), R.string.no_rates,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        presenter.onItemSelected(parent, view, position, id);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {/*No op*/ }

    @Override
    public void onCurrencySelected(Currency currency){
        ((CheckoutRecyclerView) v.findViewById(R.id.checkout_recycler)).setCurrency(currency);
        ((TotalView) v.findViewById(R.id.total_view)).setCurrency(currency);
    }

    public void setSpinnerPosition(int spinnerPosition) {
        ((Spinner) v.findViewById(R.id.spinner)).setSelection(spinnerPosition);
    }

}
