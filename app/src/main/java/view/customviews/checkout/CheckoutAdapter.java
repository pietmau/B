package view.customviews.checkout;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pietrantuono.view.R;

import java.math.BigDecimal;
import java.util.ArrayList;

import model.classes.ShoppingItem;

/**
 * Created by Maurizio Pietrantuono, maurizio.pietrantuono@gmail.com.
 */
class CheckoutAdapter extends RecyclerView.Adapter<CheckoutItemHolder> {
    private final ArrayList<ShoppingItem> items;
    private String symbol;
    private BigDecimal rate;

    public CheckoutAdapter(ArrayList<ShoppingItem> items) {
        this.items = items;
    }

    @Override
    public CheckoutItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.checkout_item, parent, false);
        return new CheckoutItemHolder(v);
    }

    @Override
    public void onBindViewHolder(CheckoutItemHolder checkoutItemHolder, int position) {
        checkoutItemHolder.setData(items.get(position),symbol,rate);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setCurrency(String symbol, String rate) {
        this.symbol=symbol;
        this.rate=new BigDecimal(rate);
        notifyDataSetChanged();
    }
}
