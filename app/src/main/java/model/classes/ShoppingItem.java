package model.classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;

/**
 * Created by Maurizio Pietrantuono, maurizio.pietrantuono@gmail.com.
 */

/**
 * We use a Parcelable so that we can pass it around
 */
public class ShoppingItem implements Parcelable{
    private final String ItemName;
    private final BigDecimal priceInPounds;
    private int quantity;
    private final String unity;

    public ShoppingItem(String itemName, BigDecimal priceInPounds, String unity) {
        this.ItemName = itemName;
        this.priceInPounds = priceInPounds;
        this.unity = unity;
    }

    protected ShoppingItem(Parcel in) {
        ItemName = in.readString();
        priceInPounds = new BigDecimal(in.readString());
        quantity = in.readInt();
        unity = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ItemName);
        dest.writeString(priceInPounds.toString());
        dest.writeInt(quantity);
        dest.writeString(unity);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ShoppingItem> CREATOR = new Parcelable.Creator<ShoppingItem>() {
        @Override
        public ShoppingItem createFromParcel(Parcel in) {
            return new ShoppingItem(in);
        }

        @Override
        public ShoppingItem[] newArray(int size) {
            return new ShoppingItem[size];
        }
    };

    public String getName() {
        return ItemName;
    }

    public BigDecimal getPriceInPounds() {
        return priceInPounds;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getUnity() {
        return unity;
    }

    public void increase() {
        if (quantity >= Integer.MAX_VALUE) return;
        quantity++;
    }

    public void decrease() {
        if (quantity <= 0) return;
        quantity--;
    }
}
