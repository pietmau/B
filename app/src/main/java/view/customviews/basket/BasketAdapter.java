package view.customviews.basket;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pietrantuono.view.R;

import java.util.ArrayList;

import model.classes.ShoppingItem;

/**
 * Created by Maurizio Pietrantuono, maurizio.pietrantuono@gmail.com.
 */
public class BasketAdapter extends RecyclerView.Adapter<BasketItemHolder> {
    private final ArrayList<ShoppingItem> items;

    public BasketAdapter(ArrayList<ShoppingItem> items) {
        this.items = items;
    }

    @Override
    public BasketItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.basket_item, parent, false);
        return new BasketItemHolder(v);
    }

    @Override
    public void onBindViewHolder(BasketItemHolder basketItemHolder, int position) {
        basketItemHolder.setData(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public ArrayList<ShoppingItem> getItems(){
        return items;
    }
}
