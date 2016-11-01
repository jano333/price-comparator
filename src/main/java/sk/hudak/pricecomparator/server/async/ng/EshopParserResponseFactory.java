package sk.hudak.pricecomparator.server.async.ng;

import sk.hudak.pricecomparator.middle.canonical.ProductAction;

import java.math.BigDecimal;

/**
 * Created by jan on 9. 7. 2016.
 */
public class EshopParserResponseFactory {

    public static final BigDecimal PRICE_UNAVIABLE = new BigDecimal(-1);

    public static EshopParserResponse createUnaviable() {
        return new EshopParserResponse()
                .setAction(ProductAction.UNKNOWN)
                .setPriceForOneItemInPackage(PRICE_UNAVIABLE)
                .setPriceForPackage(PRICE_UNAVIABLE)
                .setPriceForUnit(PRICE_UNAVIABLE);

    }
}
