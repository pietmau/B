package view.customviews.checkout;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pietrantuono.view.R;

import java.math.BigDecimal;

import converter.CurrencyConverter;
import model.classes.ShoppingItem;

/**
 * Created by Maurizio Pietrantuono, maurizio.pietrantuono@gmail.com.
 */
public class CheckoutItemHolder extends RecyclerView.ViewHolder {
    private final TextView name;
    private final TextView quantity;
    private final TextView price_in_currency;
    private final TextView total_price_in_currency;
    private final TextView currency_simbol_one;
    private final TextView currency_simbol_two;

    public CheckoutItemHolder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.name);
        quantity = (TextView) itemView.findViewById(R.id.quantity);
        price_in_currency = (TextView) itemView.findViewById(R.id.price_in_currency);
        total_price_in_currency = (TextView) itemView.findViewById(R.id.total_amount_in_currency);
        currency_simbol_one = (TextView) itemView.findViewById(R.id.currency_symbol_one);
        currency_simbol_two = (TextView) itemView.findViewById(R.id.currency_simbol_two);
    }

    public void setData(ShoppingItem data, String symbol, BigDecimal rate) {
        name.setText(data.getName() != null ? data.getName() : "");
        quantity.setText(Integer.toString(data.getQuantity()));
        if (symbol == null) {
            currency_simbol_one.setText(R.string.gbp);
            currency_simbol_two.setText(R.string.gbp);
        } else {
            currency_simbol_one.setText(symbol);
            currency_simbol_two.setText(symbol);
        }
        if (rate == null) {
            price_in_currency.setText(getPrice(data));
            BigDecimal totalPrice = CurrencyConverter.itemInGbp(data);
            total_price_in_currency.setText(totalPrice.toPlainString());
        } else {
            BigDecimal unitPrice = CurrencyConverter.fromGbpToCurr(data.getPriceInPounds(), rate);
            price_in_currency.setText(unitPrice.toPlainString());
            BigDecimal totalPrice = CurrencyConverter.itemFromGbpToCurr(data,rate);
            total_price_in_currency.setText(totalPrice.toPlainString());
        }
    }

    private String getPrice(ShoppingItem shoppingItem) {
        if (shoppingItem.getPriceInPounds().compareTo(new BigDecimal("0")) <= 0) return "" + 0;
        if (shoppingItem.getPriceInPounds().compareTo(new BigDecimal("1")) <= 0)
            return shoppingItem.getPriceInPounds() + "p";
        else return "£" + shoppingItem.getPriceInPounds();
    }


}
