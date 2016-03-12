package view.basket;

import java.util.ArrayList;

import model.classes.ShoppingItem;

/**
 * Created by Maurizio Pietrantuono, maurizio.pietrantuono@gmail.com.
 */
interface Basket {
    @SuppressWarnings("unused")
    void gotToCheckout(ArrayList<ShoppingItem> itemList);
}
