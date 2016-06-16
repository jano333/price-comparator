package sk.hudak.pricecomparator.middle.model;

/**
 * Created by jan on 13. 10. 2015.
 */
public enum ProductAction {
    IN_ACTION("Áno"),
    NON_ACTION("Nie"),
    UNKNOWN("Neudáva");

    private final String localizable;

    ProductAction(String localizable){
        this.localizable = localizable;
    }

    public String getLocalizable() {
        return localizable;
    }
}
