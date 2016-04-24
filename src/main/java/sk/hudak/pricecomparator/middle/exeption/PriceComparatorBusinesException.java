package sk.hudak.pricecomparator.middle.exeption;

/**
 * Created by jan on 24. 4. 2016.
 */
public class PriceComparatorBusinesException extends Exception {

    public PriceComparatorBusinesException(String message) {
        super(message);
    }

    public PriceComparatorBusinesException(String message, Throwable cause) {
        super(message, cause);
    }
}
