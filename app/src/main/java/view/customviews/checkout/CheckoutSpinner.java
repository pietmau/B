package view.customviews.checkout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Spinner;

import model.api.Exchangerate;
import view.checkout.MySpinnerAdapter;

/**
 * Created by Maurizio Pietrantuono, maurizio.pietrantuono@gmail.com.
 */
public class CheckoutSpinner extends Spinner {

    public void onRatesAvailable(Exchangerate.Rates rates, OnItemSelectedListener listener){
        setVisibility(View.VISIBLE);
        setAdapter(new MySpinnerAdapter(rates, getContext()));
        setOnItemSelectedListener(listener);
    }

    public CheckoutSpinner(Context context) {
        super(context);
    }

    public CheckoutSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckoutSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("NewApi")
    public CheckoutSpinner(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, int mode) {
        super(context, attrs, defStyleAttr, defStyleRes, mode);
    }

    @SuppressLint("NewApi")
    public CheckoutSpinner(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, int mode, Resources.Theme popupTheme) {
        super(context, attrs, defStyleAttr, defStyleRes, mode, popupTheme);
    }

    public CheckoutSpinner(Context context, AttributeSet attrs, int defStyleAttr, int mode) {
        super(context, attrs, defStyleAttr, mode);
    }

    public CheckoutSpinner(Context context, int mode) {
        super(context, mode);
    }
}
