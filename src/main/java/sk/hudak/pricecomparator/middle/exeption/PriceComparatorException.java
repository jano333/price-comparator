package sk.hudak.pricecomparator.middle.exeption;

/**
 * Created by jan on 13. 10. 2015.
 */
public class PriceComparatorException extends RuntimeException {

    public PriceComparatorException(String message) {
        super(message);
    }

    public PriceComparatorException(String message, Throwable cause) {
        super(message, cause);
    }
}
