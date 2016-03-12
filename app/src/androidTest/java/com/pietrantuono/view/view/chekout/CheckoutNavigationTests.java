package com.pietrantuono.view.view.chekout;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.TextView;

import com.pietrantuono.view.R;
import com.robotium.solo.Solo;

import java.math.BigDecimal;
import java.math.MathContext;

import converter.CurrencyConverter;
import view.customviews.basket.BasketRecyclerView;
import view.customviews.checkout.CheckoutRecyclerView;
import view.navigation.NavigationView;

public class CheckoutNavigationTests extends ActivityInstrumentationTestCase2<NavigationView> {
    private Solo solo;

    public CheckoutNavigationTests() {
        super(NavigationView.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testChange() throws InterruptedException {
        View item = ((BasketRecyclerView) solo.getView(R.id.basket_recycler)).getChildAt(0);
        View increase = item.findViewById(R.id.increase);
        TextView quantity = (TextView) item.findViewById(R.id.quantity);
        assertTrue(quantity.getText().toString().equals("0"));
        solo.clickOnView(increase);
        solo.clickOnView(increase);
        solo.clickOnView(solo.getView(R.id.basket_fab));
        solo.waitForView(R.id.checkout_recycler);
        item = ((CheckoutRecyclerView) solo.getView(R.id.checkout_recycler)).getChildAt(0);
        String result = ((TextView) item.findViewById(R.id.total_amount_in_currency)).getText().toString();
        String expected = (new BigDecimal("0.95").multiply(new BigDecimal(2))).setScale(CurrencyConverter.SCALE, CurrencyConverter.BANKERS_ROUNDING_MODE).toPlainString();
        assertEquals(result, expected);
        View v = solo.getView(R.id.total_view);
        result = ((TextView) v.findViewById(R.id.total_amount)).getText().toString();
        assertEquals(result, expected);

        solo.clickOnView(solo.getView(R.id.change));
        solo.waitForView(R.id.basket_recycler);
        item = ((BasketRecyclerView) solo.getView(R.id.basket_recycler)).getChildAt(0);
        increase = item.findViewById(R.id.increase);
        solo.clickOnView(increase);
        solo.clickOnView(solo.getView(R.id.basket_fab));
        solo.waitForView(R.id.checkout_recycler);
        item = ((CheckoutRecyclerView) solo.getView(R.id.checkout_recycler)).getChildAt(0);
        result = ((TextView) item.findViewById(R.id.total_amount_in_currency)).getText().toString();
        expected = (new BigDecimal("0.95").multiply(new BigDecimal(3))).setScale(CurrencyConverter.SCALE, CurrencyConverter.BANKERS_ROUNDING_MODE).toPlainString();
        assertEquals(result, expected);
        v = solo.getView(R.id.total_view);
        result = ((TextView) v.findViewById(R.id.total_amount)).getText().toString();
        assertEquals(result, expected);

    }

    public void testGbpTotalConfigChange() throws InterruptedException {
        solo.setActivityOrientation(Solo.PORTRAIT);
        View item = ((BasketRecyclerView) solo.getView(R.id.basket_recycler)).getChildAt(0);
        View increase = item.findViewById(R.id.increase);
        TextView quantity = (TextView) item.findViewById(R.id.quantity);
        assertTrue(quantity.getText().toString().equals("0"));
        solo.clickOnView(increase);
        solo.clickOnView(increase);
        solo.clickOnView(solo.getView(R.id.basket_fab));
        solo.waitForView(R.id.checkout_recycler);
        item = ((CheckoutRecyclerView) solo.getView(R.id.checkout_recycler)).getChildAt(0);
        String result = ((TextView) item.findViewById(R.id.total_amount_in_currency)).getText().toString();
        String expected = (new BigDecimal("0.95").multiply(new BigDecimal(2))).setScale(CurrencyConverter.SCALE, CurrencyConverter.BANKERS_ROUNDING_MODE).toPlainString();
        assertEquals(result, expected);
        View v = solo.getView(R.id.total_view);
        result = ((TextView) v.findViewById(R.id.total_amount)).getText().toString();
        assertEquals(result, expected);
        solo.setActivityOrientation(Solo.LANDSCAPE);
        solo.waitForView(R.id.checkout_recycler);
        item = ((CheckoutRecyclerView) solo.getView(R.id.checkout_recycler)).getChildAt(0);
        result = ((TextView) item.findViewById(R.id.total_amount_in_currency)).getText().toString();
        expected = (new BigDecimal("0.95").multiply(new BigDecimal(2))).setScale(CurrencyConverter.SCALE, CurrencyConverter.BANKERS_ROUNDING_MODE).toPlainString();
        assertEquals(result, expected);
        v = solo.getView(R.id.total_view);
        result = ((TextView) v.findViewById(R.id.total_amount)).getText().toString();
        assertEquals(result, expected);
    }

} 