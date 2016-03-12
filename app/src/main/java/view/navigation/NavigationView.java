package view.navigation;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.pietrantuono.view.R;

import java.util.ArrayList;

import model.classes.ShoppingItem;
import view.basket.BasketView;
import view.checkout.CheckoutView;

public class NavigationView extends AppCompatActivity implements BasketView.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // If we are starting up
        if (savedInstanceState == null) {
            goToBasket();
        }

    }

    @Override
    public void goToCheckout(ArrayList<ShoppingItem> items) {
        FragmentManager manager = getSupportFragmentManager();
        // We create a new instance because we need to pass the items as args
        CheckoutView checkoutView = CheckoutView.newInstance();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(CheckoutView.ITEMS, items);
        // We set the items modified by the user as args
        checkoutView.setArguments(bundle);

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, checkoutView, CheckoutView.TAG);
        // We clear the backstack from previous transaction
        if (manager.getBackStackEntryCount() > 0) {
            manager.popBackStack();
        }
        // We add this one to the backstack so the user can navigate back pressing the back button
        // or pressing the MODIFY button
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void goToBasket() {
        FragmentManager manager = getSupportFragmentManager();
        BasketView basketView = BasketView.newInstance();
        // We create a new fragment every time, because:
        // - either the app is starting
        // - or the user checked out,
        // in both cases we want a clean state
        // We we do not add it to the backstack, if the use presses back the app closes

        // We clear the backstack from previous transaction
        if (manager.getBackStackEntryCount() > 0) {
            manager.popBackStack();
        }
        manager.beginTransaction().replace(R.id.container, basketView, BasketView.TAG).commit();
    }

    public void goBackToBasket() {
        // To navigate back if user presses MODIFY button
        FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() > 0) manager.popBackStack();
    }
}
