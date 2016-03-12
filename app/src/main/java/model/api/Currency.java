package model.api;

/**
 * Created by Maurizio Pietrantuono, maurizio.pietrantuono@gmail.com.
 */
public class Currency {
    private final String symbol;
    private final String rate;

    public String getRate() {
        return rate;
    }

    public String getSymbol() {
        return symbol;
    }

    public Currency(String symbol, String rate) {
        this.rate = rate;
        this.symbol = symbol;
    }
}
