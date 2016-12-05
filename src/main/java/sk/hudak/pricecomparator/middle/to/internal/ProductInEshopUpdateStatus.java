package sk.hudak.pricecomparator.middle.to.internal;

/**
 * Status o stave poslednej aktualizacii infomacii o produkte.
 * <p>
 * Created by jan on 3. 12. 2016.
 */
public enum ProductInEshopUpdateStatus {
    /**
     * aktualizacia prebehla uspesne
     */
    OK,

    /**
     * produkt je momentalne nedostupny
     */
    UNAVAILABLE,

    /**
     * produkt uz neexistuje
     */
    NOT_EXIST,

    /**
     * interna chyby pri aktualizacii
     */
    ERROR;
}
