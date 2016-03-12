package api;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.TextView;

import com.pietrantuono.view.R;
import com.robotium.solo.Solo;

import api.mocks.AudModule;
import application.App;
import model.api.dagger.ApiModule;
import model.api.dagger.DaggerApiComponent;
import view.customviews.basket.BasketRecyclerView;
import view.customviews.checkout.CheckoutSpinner;
import view.navigation.NavigationView;

public class TestMockApiAUD extends ActivityInstrumentationTestCase2<NavigationView> {
    private Solo solo;
    private NavigationView mActivity;


    @SuppressWarnings("unused")
    public TestMockApiAUD() {
        super(NavigationView.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = getActivity();
        solo = new Solo(getInstrumentation(), getActivity());
        App app = (App) getInstrumentation().getTargetContext().getApplicationContext();
        DaggerApiComponent.builder().apiModule(new AudModule()).build().inject(app);
    }

    public void testAUD() throws InterruptedException {
        View item = ((BasketRecyclerView) solo.getView(R.id.basket_recycler)).getChildAt(0);
        View increase = item.findViewById(R.id.increase);
        TextView quantity = (TextView) item.findViewById(R.id.quantity);
        assertTrue(quantity.getText().toString().equals("0"));
        solo.clickOnView(increase);
        solo.clickOnView(increase);
        solo.clickOnView(solo.getView(R.id.basket_fab));
        solo.waitForView(R.id.spinner);
        final CheckoutSpinner spinner= (CheckoutSpinner) solo.getView(R.id.spinner);
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                spinner.setSelection(2);
            }
        });
        assertTrue(solo.waitForText("AUD"));
        TextView showing= (TextView) spinner.getSelectedView();
        String text=showing.getText().toString();
        assertTrue(text.contains("AUD"));
        assertTrue(text.contains("1.1"));
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        getInstrumentation().getTargetContext().getApplicationContext();
        App app = (App) getInstrumentation().getTargetContext().getApplicationContext();
        DaggerApiComponent.builder().apiModule(new ApiModule()).build().inject(app);
    }
}