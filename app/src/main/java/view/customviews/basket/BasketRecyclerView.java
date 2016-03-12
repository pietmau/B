package view.customviews.basket;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import java.math.BigDecimal;
import java.util.ArrayList;

import model.classes.ShoppingItem;

/**
 * Created by Maurizio Pietrantuono, maurizio.pietrantuono@gmail.com.
 */
public class BasketRecyclerView extends RecyclerView {
    private ArrayList<ShoppingItem> items;

    public BasketRecyclerView(Context context) {
        super(context);
        init();
    }

    public BasketRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BasketRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setLayoutManager(new LinearLayoutManager(getContext()));
        BasketAdapter adapter = new BasketAdapter(getItems());
        setAdapter(adapter);
    }

    private ArrayList<ShoppingItem> getItems() {
        if (items == null) {
            items = createItemsFromScratch();
        }
        return items;
    }

    private ArrayList<ShoppingItem> createItemsFromScratch() {
        ArrayList<ShoppingItem> list = new ArrayList<>();
        list.add(new ShoppingItem("Peas", new BigDecimal("0.9500"), "Bag"));
        list.add(new ShoppingItem("Eggs", new BigDecimal("2.1000"), "Dozen"));
        list.add(new ShoppingItem("Milk", new BigDecimal("1.3000"), "Bottle"));
        list.add(new ShoppingItem("Beans", new BigDecimal("0.7300"), "Can"));
        return list;
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        CustomSavedState customSavedState = new CustomSavedState(superState);
        customSavedState.savedItems = items;
        return customSavedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if ((state instanceof CustomSavedState)) {
            CustomSavedState customSavedState = (CustomSavedState) state;
            super.onRestoreInstanceState(customSavedState.getSuperState());
            items = customSavedState.savedItems;
            init();
        } else {
            super.onRestoreInstanceState(state);
        }
    }

    public static class CustomSavedState extends BaseSavedState {
        public ArrayList<ShoppingItem> savedItems;

        public CustomSavedState(Parcelable superState) {
            super(superState);
        }

        private CustomSavedState(Parcel in) {
            super(in);
            if (in.readByte() == 0x01) {
                savedItems = new ArrayList<>();
                in.readList(savedItems, ShoppingItem.class.getClassLoader());
            } else {
                savedItems = null;
            }
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            if (savedItems == null) {
                dest.writeByte((byte) (0x00));
            } else {
                dest.writeByte((byte) (0x01));
                dest.writeList(savedItems);
            }
        }

        @SuppressWarnings("unused")
        public static final Parcelable.Creator<CustomSavedState> CREATOR = new Parcelable.Creator<CustomSavedState>() {
            @Override
            public CustomSavedState createFromParcel(Parcel in) {
                return new CustomSavedState(in);
            }

            @Override
            public CustomSavedState[] newArray(int size) {
                return new CustomSavedState[size];
            }
        };
    }
}
