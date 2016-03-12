package view.checkout;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pietrantuono.view.R;

import java.lang.reflect.Field;

import model.api.Exchangerate;

/**
 * Created by Maurizio Pietrantuono, maurizio.pietrantuono@gmail.com.
 */
public class MySpinnerAdapter extends BaseAdapter {
    private final Exchangerate.Rates rates;
    private final Context context;

    public MySpinnerAdapter(Exchangerate.Rates rates, Context context) {
        this.rates = rates;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (rates.getClass().getFields().length > 0)
            return rates.getClass().getFields().length + 2;
        else return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(context);
        if (position >= 2) {
            Field field = rates.getClass().getFields()[position-2];
            String symbol = field.getName();
            String value = null;
            try {
                value = (String) field.get(rates);
            } // this cannot happen
            catch (Exception ignored) {}
            textView.setText(symbol + " " + value);
        }
        else if(position==1){
            textView.setText(context.getString(R.string.gbp)+ " 1");
        }
        else if (position==0){
            textView.setText(context.getString(R.string.currency));
        }
        return textView;
    }

}
