package view.customviews.checkout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pietrantuono.view.R;

import java.math.BigDecimal;
import java.util.ArrayList;

import converter.CurrencyConverter;
import model.api.Currency;
import model.classes.ShoppingItem;

/**
 * Created by Maurizio Pietrantuono, maurizio.pietrantuono@gmail.com.
 */
public class TotalView extends LinearLayout {
    private TextView total_amount;
    private TextView total_currency;
    private ArrayList<ShoppingItem> items;
    private String symbol;
    private String rate;

    public TotalView(Context context) {
        super(context);
        init();
    }

    public TotalView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TotalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @SuppressLint("NewApi")
    public TotalView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private void init() {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.total_view, this);
        total_amount = (TextView) findViewById(R.id.total_amount);
        total_currency = (TextView) findViewById(R.id.total_currency);
        setValues();
    }

    private void setValues() {
        if (rate != null && symbol != null) {
            setTotalInCurrency();
        } else {
            setTotalInGbp();
        }
    }

    private void setTotalInCurrency() {
        BigDecimal total = CurrencyConverter.calculateTotalInCurr(items, new BigDecimal(rate));
        total_amount.setText("" + total.toPlainString());
        total_currency.setText(symbol);
    }

    private void setTotalInGbp() {
        if (items == null || items.size() <= 0) return;
        BigDecimal total = new BigDecimal("0");
        for (ShoppingItem shoppingItem : items) {
            total = CurrencyConverter.calculateTotalInGbp(items);
        }
        total_amount.setText("" + total.toPlainString());
        total_currency.setText(getResources().getString(R.string.gbp));
    }

    public void setCurrency(Currency currency) {
        if (currency == null) return;
        this.symbol = currency.getSymbol();
        this.rate = currency.getRate();
        setValues();
    }

    public void setItems(ArrayList<ShoppingItem> items) {
        this.items = items;
        setValues();
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
            symbol = customSavedState.savedSymbol;
            rate = customSavedState.savedRate;
            setValues();
        } else {
            super.onRestoreInstanceState(state);
        }
    }


    public static class CustomSavedState extends BaseSavedState {
        public ArrayList<ShoppingItem> savedItems;
        public String savedSymbol;
        public String savedRate;

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
            if (in.readByte() == 0x01) {
                savedSymbol = in.readString();
            } else {
                savedSymbol = null;
            }
            if (in.readByte() == 0x01) {
                savedRate = in.readString();
            } else {
                savedRate = null;
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
            if (savedSymbol == null) {
                dest.writeByte((byte) (0x00));
            } else {
                dest.writeByte((byte) (0x01));
                dest.writeString(savedSymbol);
            }
            if (savedRate == null) {
                dest.writeByte((byte) (0x00));
            } else {
                dest.writeByte((byte) (0x01));
                dest.writeString(savedRate);
            }
        }

        @SuppressWarnings("unused")
        public static final Creator<CustomSavedState> CREATOR = new Creator<CustomSavedState>() {
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
