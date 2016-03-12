package converter;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;

import model.classes.ShoppingItem;

/**
 * Created by Maurizio Pietrantuono, maurizio.pietrantuono@gmail.com.
 */
public class CurrencyConverter {
    public static final int SCALE = 4;
    public static final RoundingMode BANKERS_ROUNDING_MODE = RoundingMode.HALF_EVEN;

    public static BigDecimal itemFromGbpToCurr(ShoppingItem item, BigDecimal rate) {//OK, tested
        BigDecimal priceInCurr=fromGbpToCurr(item.getPriceInPounds(),rate);
        return priceInCurr.multiply(new BigDecimal(item.getQuantity()));
    }

    public static BigDecimal calculateTotalInCurr(ArrayList<ShoppingItem> items, BigDecimal rate) {//OK, tested
        if (items == null || items.size() <= 0) return new BigDecimal("0");
        BigDecimal totalInCurr = new BigDecimal("0");
        for (ShoppingItem item : items) {
            BigDecimal itemPiceinCurr=itemFromGbpToCurr(item, rate);
            totalInCurr = totalInCurr.add(itemPiceinCurr);
        }
        return totalInCurr;
    }

    public static BigDecimal itemInGbp(ShoppingItem item){//OK, tested
        return new BigDecimal(item.getQuantity()).multiply(item.getPriceInPounds());
    }

    public static BigDecimal calculateTotalInGbp(ArrayList<ShoppingItem> items) {//OK tested
        if (items == null || items.size() <= 0) return new BigDecimal("0");
        BigDecimal totalInGbp = new BigDecimal("0");
        for (ShoppingItem item : items) {
            totalInGbp = totalInGbp.add(itemInGbp(item));
        }
        return totalInGbp;
    }

    public static BigDecimal fromGbpToCurr(BigDecimal priceInGbP, BigDecimal rate){
        return rate.multiply(priceInGbP).setScale(SCALE,BANKERS_ROUNDING_MODE);
    }

}
