package api;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.TextView;

import com.pietrantuono.view.R;
import com.robotium.solo.Condition;
import com.robotium.solo.Solo;

import java.math.BigDecimal;
import java.math.MathContext;

import api.mocks.ErrorModule;
import application.App;
import converter.CurrencyConverter;
import model.api.dagger.ApiModule;
import model.api.dagger.DaggerApiComponent;
import view.customviews.basket.BasketRecyclerView;
import view.customviews.checkout.CheckoutRecyclerView;
import view.navigation.NavigationView;

public class TestMockApiNoResponse extends ActivityInstrumentationTestCase2<NavigationView> {
    private Solo solo;
    private NavigationView mActivity;

    @SuppressWarnings("unused")
    public TestMockApiNoResponse() {
        super(NavigationView.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = getActivity();
        solo = new Solo(getInstrumentation(), getActivity());
        App app = (App) getInstrumentation().getTargetContext().getApplicationContext();
        DaggerApiComponent.builder().apiModule(new ErrorModule()).build().inject(app);
    }

    public void testNoResponse() throws InterruptedException {

        solo.clickOnView(solo.getView(R.id.basket_fab));
        assertTrue(solo.waitForView(R.id.progress));
        View progress = solo.getView(R.id.progress);
        solo.sleep(3 * 1000);// Fake response takes 2 secs
        assertTrue(progress.getVisibility()==View.INVISIBLE);
        View spinner = solo.getView(R.id.spinner);
        assertTrue(spinner.getVisibility() == View.INVISIBLE);
        assertTrue(solo.waitForText(mActivity.getResources().getString(R.string.no_rates)));
    }

    public void testNoResponseConfigChange() throws InterruptedException {
        solo.setActivityOrientation(Solo.PORTRAIT);
        solo.clickOnView(solo.getView(R.id.basket_fab));
        assertTrue(solo.waitForView(R.id.progress));
        View progress = solo.getView(R.id.progress);
        solo.sleep(3 * 1000);// Fake response takes 2 secs
        assertTrue(progress.getVisibility() == View.INVISIBLE);
        View spinner = solo.getView(R.id.spinner);
        assertTrue(spinner.getVisibility() == View.INVISIBLE);
        assertTrue(solo.waitForText(mActivity.getResources().getString(R.string.no_rates)));
        solo.sleep(5 * 1000);//Wait for the toast to go away
        solo.setActivityOrientation(Solo.LANDSCAPE);
        progress = solo.getView(R.id.progress);
        solo.sleep(3 * 1000);// Fake response takes 2 secs
        assertTrue(progress.getVisibility() == View.INVISIBLE);
        spinner = solo.getView(R.id.spinner);
        assertTrue(spinner.getVisibility() == View.INVISIBLE);
        assertTrue(!solo.waitForText(mActivity.getResources().getString(R.string.no_rates),1,1*1000));
    }


    public void testNoResponseConfigChange2() throws InterruptedException {
        solo.setActivityOrientation(Solo.PORTRAIT);
        solo.clickOnView(solo.getView(R.id.basket_fab));
        assertTrue(solo.waitForView(R.id.progress));
        View progress = solo.getView(R.id.progress);
        solo.sleep(3 * 1000);// Fake response takes 2 secs
        assertTrue(progress.getVisibility() == View.INVISIBLE);
        View spinner = solo.getView(R.id.spinner);
        assertTrue(spinner.getVisibility() == View.INVISIBLE);
        assertTrue(solo.waitForText(mActivity.getResources().getString(R.string.no_rates)));
        solo.sleep(5 * 1000);//Wait for the toast to go away
        solo.setActivityOrientation(Solo.LANDSCAPE);
        progress = solo.getView(R.id.progress);
        solo.sleep(3 * 1000);// Fake response takes 2 secs
        assertTrue(progress.getVisibility() == View.INVISIBLE);
        spinner = solo.getView(R.id.spinner);
        assertTrue(spinner.getVisibility() == View.INVISIBLE);
        assertTrue(!solo.waitForText(mActivity.getResources().getString(R.string.no_rates), 1, 1 * 1000));
        solo.setActivityOrientation(Solo.PORTRAIT);
        progress = solo.getView(R.id.progress);
        assertTrue(progress.getVisibility() == View.INVISIBLE);
        spinner = solo.getView(R.id.spinner);
        assertTrue(spinner.getVisibility() == View.INVISIBLE);
        assertTrue(!solo.waitForText(mActivity.getResources().getString(R.string.no_rates),1,1*1000));
    }


    public void testNoResponseConfigChange3() throws InterruptedException {
        View item = ((BasketRecyclerView) solo.getView(R.id.basket_recycler)).getChildAt(0);
        View increase = item.findViewById(R.id.increase);
        TextView quantity = (TextView) item.findViewById(R.id.quantity);
        assertTrue(quantity.getText().toString().equals("0"));
        solo.clickOnView(increase);
        solo.clickOnView(increase);
        solo.clickOnView(solo.getView(R.id.basket_fab));
        assertTrue(solo.waitForView(R.id.progress));
        View progress = solo.getView(R.id.progress);
        View spinner = solo.getView(R.id.spinner);
        solo.waitForCondition(new Condition() {
            @Override
            public boolean isSatisfied() {
                View spinner = solo.getView(R.id.spinner);
                return spinner.getVisibility() == View.INVISIBLE;
            }
        }, 2 * 1000);

        assertTrue(spinner.getVisibility() == View.INVISIBLE);
        assertTrue(solo.waitForText(mActivity.getResources().getString(R.string.no_rates)));


        solo.waitForView(R.id.checkout_recycler);
        item = ((CheckoutRecyclerView) solo.getView(R.id.checkout_recycler)).getChildAt(0);
        String result = ((TextView) item.findViewById(R.id.total_amount_in_currency)).getText().toString();
        String expected = (new BigDecimal("0.9500").multiply(new BigDecimal(2)).setScale(CurrencyConverter.SCALE,CurrencyConverter.BANKERS_ROUNDING_MODE).toPlainString());
        assertEquals(result, expected);



        solo.sleep(5 * 1000);//Wait for the toast to go away
        solo.setActivityOrientation(Solo.LANDSCAPE);
        progress = solo.getView(R.id.progress);
        solo.sleep(3 * 1000);// Fake response takes 2 secs
        assertTrue(progress.getVisibility() == View.INVISIBLE);
        spinner = solo.getView(R.id.spinner);
        assertTrue(spinner.getVisibility() == View.INVISIBLE);
        assertTrue(!solo.waitForText(mActivity.getResources().getString(R.string.no_rates), 1, 1 * 1000));
        solo.setActivityOrientation(Solo.PORTRAIT);
        progress = solo.getView(R.id.progress);
        assertTrue(progress.getVisibility() == View.INVISIBLE);
        spinner = solo.getView(R.id.spinner);
        assertTrue(spinner.getVisibility() == View.INVISIBLE);
        assertTrue(!solo.waitForText(mActivity.getResources().getString(R.string.no_rates),1,1*1000));

        solo.waitForView(R.id.checkout_recycler);
        item = ((CheckoutRecyclerView) solo.getView(R.id.checkout_recycler)).getChildAt(0);
        result = ((TextView) item.findViewById(R.id.total_amount_in_currency)).getText().toString();
        expected = (new BigDecimal("0.95").multiply(new BigDecimal(2)).setScale(CurrencyConverter.SCALE,CurrencyConverter.BANKERS_ROUNDING_MODE).toPlainString());
        assertEquals(result, expected);

    }


    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        App app = (App) getInstrumentation().getTargetContext().getApplicationContext();
        DaggerApiComponent.builder().apiModule(new ApiModule()).build().inject(app);
    }
}