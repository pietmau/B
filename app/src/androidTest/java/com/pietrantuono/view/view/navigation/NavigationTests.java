package com.pietrantuono.view.view.navigation;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import view.navigation.NavigationView;
import com.pietrantuono.view.R;
import com.robotium.solo.Solo;

public class NavigationTests extends ActivityInstrumentationTestCase2<NavigationView> {
    private Solo solo;

    public NavigationTests() {
        super(NavigationView.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testNavigationToCheckout() {
        View basketFab = solo.getView(R.id.basket_fab);
        assertNotNull(basketFab);
        solo.clickOnView(basketFab);
        assertTrue(solo.waitForView(R.id.checkout));
        View checkoutFab = solo.getView(R.id.checkout);
        assertNotNull(checkoutFab);
        solo.clickOnView(checkoutFab);
        assertTrue(solo.waitForView(R.id.basket_fab));
        basketFab = solo.getView(R.id.basket_fab);
        assertNotNull(basketFab);
    }

    public void testNavigationPressBackButton() {
        View basketFab = solo.getView(R.id.basket_fab);
        assertNotNull(basketFab);
        solo.clickOnView(basketFab);
        assertTrue(solo.waitForView(R.id.checkout));
        View checkoutFab = solo.getView(R.id.checkout);
        assertNotNull(checkoutFab);
        solo.goBack();
        assertTrue(solo.waitForView(R.id.basket_fab));
        basketFab = solo.getView(R.id.basket_fab);
        assertNotNull(basketFab);
    }

    public void testNavigationPressCheckOutFab() {
        View basketFab = solo.getView(R.id.basket_fab);
        assertNotNull(basketFab);
        solo.clickOnView(basketFab);
        assertTrue(solo.waitForView(R.id.checkout));
        View checkoutFab = solo.getView(R.id.checkout);
        assertNotNull(checkoutFab);
        solo.clickOnView(checkoutFab);
        assertTrue(solo.waitForView(R.id.basket_fab));
        basketFab = solo.getView(R.id.basket_fab);
        assertNotNull(basketFab);
    }



} 