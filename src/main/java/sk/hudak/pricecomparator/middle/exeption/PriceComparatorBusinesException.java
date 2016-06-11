package sk.hudak.pricecomparator.middle.exeption;

/**
 * Created by jan on 24. 4. 2016.
 */
public class PriceComparatorBusinesException extends Exception {

    private String[] params;

    public PriceComparatorBusinesException(String msgCode) {
        super(msgCode);
    }
    public PriceComparatorBusinesException(String msgCode, String... params) {
        super(msgCode);
        this.params = params;
    }

    public PriceComparatorBusinesException(String msgCode, Throwable cause) {
        super(msgCode, cause);
    }

    public String[] getParams() {
        return params;
    }
}
