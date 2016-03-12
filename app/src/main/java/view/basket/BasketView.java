package view.basket;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.pietrantuono.view.R;
import java.util.ArrayList;
import model.classes.ShoppingItem;
import view.customviews.basket.BasketAdapter;
import view.customviews.basket.BasketRecyclerView;


public class BasketView extends Fragment implements Basket {
    public static final String TAG = "BasketFragment";
    private Callback callback;

    public BasketView() {}

    public static BasketView newInstance() {
        return new BasketView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Here we use a custom RecyclerView so we can easily add new items to our store in future,
        // we use a subclass of it so we can save the state (the adapter) inside the recycler itself,
        // code is cleaner and more modular
        View v =inflater.inflate(R.layout.fragment_basket, container, false);
        final BasketRecyclerView basketRecyclerView = (BasketRecyclerView) v.findViewById(R.id.basket_recycler);
        // The recycler contains user inputs, we pass it back to go to checkout
        v.findViewById(R.id.basket_fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // We get the items, as modified by the user from the adapter and we pass them to
                // the checkout fragment
                gotToCheckout(((BasketAdapter) basketRecyclerView.getAdapter()).getItems());

            }
        });
        return v;
    }

    @Override
    public void gotToCheckout(ArrayList<ShoppingItem> itemList) {
        if (callback != null)callback.goToCheckout(itemList);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callback= (Callback) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback=null;
    }

    public interface Callback{
        void goToCheckout(ArrayList<ShoppingItem> items);
    }
}
