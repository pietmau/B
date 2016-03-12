package view.customviews.basket;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pietrantuono.view.R;

import java.math.BigDecimal;

import model.classes.ShoppingItem;

/**
 * Created by Maurizio Pietrantuono, maurizio.pietrantuono@gmail.com.
 */
public class BasketItemHolder extends RecyclerView.ViewHolder {
    private final TextView name;
    private final TextView price;
    private final TextView unity;
    private final TextView quantity;
    private ShoppingItem shoppingItem;

    public BasketItemHolder(View itemView) {
        super(itemView);
        name= (TextView) itemView.findViewById(R.id.name);
        price= (TextView) itemView.findViewById(R.id.price_in_currency);
        unity= (TextView) itemView.findViewById(R.id.unity);
        quantity = (TextView) itemView.findViewById(R.id.quantity);
        itemView.findViewById(R.id.increase).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoppingItem.increase();
                setData(shoppingItem);
            }
        });
        itemView.findViewById(R.id.decrease).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoppingItem.decrease();
                setData(shoppingItem);
            }
        });
    }

    public void setData(ShoppingItem data){
        this.shoppingItem=data;
        name.setText(data.getName()!=null?data.getName():"");
        price.setText(getPrice(data));
        unity.setText(unity.getContext().getResources().getString(R.string.a)+(data.getUnity()!=null?data.getUnity():""));
        quantity.setText(Integer.toString(data.getQuantity()));
    }

    private String getPrice(ShoppingItem shoppingItem){
        if(shoppingItem.getPriceInPounds().compareTo(new BigDecimal("0"))<=0)return ""+0;
        if(shoppingItem.getPriceInPounds().compareTo(new BigDecimal("1"))<=0)return shoppingItem.getPriceInPounds().toPlainString()+"p";
        else return "£"+shoppingItem.getPriceInPounds().toPlainString();

    }
}
