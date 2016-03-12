package com.pietrantuono.view.view.navigation;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import view.navigation.NavigationView;
import com.pietrantuono.view.R;
import com.robotium.solo.Solo;

public class NavigationConfigChanges extends ActivityInstrumentationTestCase2<NavigationView> {
    private Solo solo;

    public NavigationConfigChanges() {
        super(NavigationView.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testNavigationToCheckout() {
        solo.setActivityOrientation(Solo.PORTRAIT);
        assertTrue(solo.waitForView(R.id.basket_fab));
        View basketFab = solo.getView(R.id.basket_fab);
        assertNotNull(basketFab);
        solo.setActivityOrientation(Solo.LANDSCAPE);
        assertTrue(solo.waitForView(R.id.basket_fab));
        basketFab = solo.getView(R.id.basket_fab);
        solo.clickOnView(basketFab);
        assertTrue(solo.waitForView(R.id.checkout));
        View checkoutFab = solo.getView(R.id.checkout);
        assertNotNull(checkoutFab);
        solo.setActivityOrientation(Solo.PORTRAIT);
        assertTrue(solo.waitForView(R.id.checkout));
        checkoutFab = solo.getView(R.id.checkout);
        assertNotNull(checkoutFab);
        solo.clickOnView(checkoutFab);
        assertTrue(solo.waitForView(R.id.basket_fab));
        basketFab = solo.getView(R.id.basket_fab);
        assertNotNull(basketFab);
    }

    public void testNavigationPressBackButtonConfigChange() {
        solo.setActivityOrientation(Solo.PORTRAIT);
        assertTrue(solo.waitForView(R.id.basket_fab));
        View basketFab = solo.getView(R.id.basket_fab);
        assertNotNull(basketFab);
        solo.setActivityOrientation(Solo.LANDSCAPE);
        assertTrue(solo.waitForView(R.id.basket_fab));
        basketFab = solo.getView(R.id.basket_fab);
        assertNotNull(basketFab);
        solo.clickOnView(basketFab);
        assertTrue(solo.waitForView(R.id.checkout));
        View checkoutFab = solo.getView(R.id.checkout);
        assertNotNull(checkoutFab);
        solo.setActivityOrientation(Solo.PORTRAIT);
        assertTrue(solo.waitForView(R.id.checkout));
        checkoutFab = solo.getView(R.id.checkout);
        assertNotNull(checkoutFab);
        solo.goBack();
        assertTrue(solo.waitForView(R.id.basket_fab));
        basketFab = solo.getView(R.id.basket_fab);
        assertNotNull(basketFab);
    }

    public void testNavigationPressCheckOutFabConfigChanges() {
        solo.setActivityOrientation(Solo.PORTRAIT);
        assertTrue(solo.waitForView(R.id.basket_fab));
        View basketFab = solo.getView(R.id.basket_fab);
        assertNotNull(basketFab);
        solo.setActivityOrientation(Solo.LANDSCAPE);
        assertTrue(solo.waitForView(R.id.basket_fab));
        basketFab = solo.getView(R.id.basket_fab);
        assertNotNull(basketFab);
        solo.clickOnView(basketFab);
        assertTrue(solo.waitForView(R.id.checkout));
        View checkoutFab = solo.getView(R.id.checkout);
        assertNotNull(checkoutFab);
        solo.setActivityOrientation(Solo.PORTRAIT);
        assertTrue(solo.waitForView(R.id.checkout));
        checkoutFab = solo.getView(R.id.checkout);
        assertNotNull(checkoutFab);
        solo.clickOnView(checkoutFab);
        assertTrue(solo.waitForView(R.id.basket_fab));
        basketFab = solo.getView(R.id.basket_fab);
        assertNotNull(basketFab);
    }

//    public void testRotation1() throws InterruptedException {
//        assertTrue(solo.waitForText("Article 8"));
//        mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        Thread.sleep(2 * 1000);
//        mActivity = (MainActivity) solo.getCurrentActivity();
//        assertTrue(solo.searchText("Article 8"));
//        solo.clickOnText("Article 8");
//        mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        Thread.sleep(2 * 1000);
//        mActivity = (MainActivity) solo.getCurrentActivity();
//        assertTrue(solo.waitForText("laborum. Article 8"));
//        solo.goBack();
//
//
//    }


} 