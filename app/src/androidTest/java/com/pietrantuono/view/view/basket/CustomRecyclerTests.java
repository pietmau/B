package com.pietrantuono.view.view.basket;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.TextView;

import com.pietrantuono.view.R;
import com.robotium.solo.Solo;

import view.navigation.NavigationView;
import view.customviews.basket.BasketRecyclerView;

public class CustomRecyclerTests extends ActivityInstrumentationTestCase2<NavigationView> {
    private Solo solo;

    public CustomRecyclerTests() {
        super(NavigationView.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testItemsButtons() throws InterruptedException {
        View item= ((BasketRecyclerView) solo.getView(R.id.basket_recycler)).getChildAt(0);
        View increase = item.findViewById(R.id.increase);
        View decrease = item.findViewById(R.id.decrease);
        TextView quantity= (TextView) item.findViewById(R.id.quantity);
        assertTrue(quantity.getText().toString().equals("0"));
        solo.clickOnView(increase);
        solo.clickOnView(increase);
        assertTrue(solo.waitForText("2"));
        // Litmus
        assertTrue(!quantity.getText().toString().equals("0"));
        solo.clickOnView(decrease);
        assertTrue(solo.waitForText("1"));
        // Litmus
        assertTrue(!quantity.getText().toString().equals("2"));
    }

    public void testItemsButtonsConfigChange() throws InterruptedException {
        solo.setActivityOrientation(Solo.PORTRAIT);
        View item= ((BasketRecyclerView) solo.getView(R.id.basket_recycler)).getChildAt(0);
        View increase = item.findViewById(R.id.increase);
        View decrease = item.findViewById(R.id.decrease);
        TextView quantity= (TextView) item.findViewById(R.id.quantity);
        assertTrue(quantity.getText().toString().equals("0"));
        solo.clickOnView(increase);
        solo.clickOnView(increase);
        assertTrue(solo.waitForText("2"));
        // Litmus
        assertTrue(!quantity.getText().toString().equals("0"));
        solo.setActivityOrientation(Solo.LANDSCAPE);
        // We need to get the views again because they have been recreated
        item= ((BasketRecyclerView) solo.getView(R.id.basket_recycler)).getChildAt(0);
        decrease = item.findViewById(R.id.decrease);
        quantity= (TextView) item.findViewById(R.id.quantity);
        solo.clickOnView(decrease);
        assertTrue(solo.waitForText("1"));
        // Litmus
        assertTrue(!quantity.getText().toString().equals("2"));
    }
} 