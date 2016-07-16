package sk.hudak.pricecomparator.server.async.ng;

import sk.hudak.pricecomparator.middle.model.ProductAction;

import java.math.BigDecimal;

/**
 * Created by jan on 9. 7. 2016.
 */
public class EshopParserResponseNgFactory {

    public static final BigDecimal PRICE_UNAVIABLE = new BigDecimal(-1);

    public static EshopParserResponseNg createUnaviable() {
        return new EshopParserResponseNg()
                .setAction(ProductAction.UNKNOWN)
                .setPriceForOneItemInPackage(PRICE_UNAVIABLE)
                .setPriceForPackage(PRICE_UNAVIABLE)
                .setPriceForUnit(PRICE_UNAVIABLE);

    }
}
