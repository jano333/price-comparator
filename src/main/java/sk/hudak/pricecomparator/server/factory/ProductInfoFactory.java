package sk.hudak.pricecomparator.server.factory;

import sk.hudak.pricecomparator.middle.api.model.ProductAction;
import sk.hudak.pricecomparator.middle.api.model.EshopProductInfo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 14. 10. 2015.
 */
public class ProductInfoFactory {

    //TODO do api
    public static final BigDecimal PRICE_UNAVIABLE = new BigDecimal(-1);

    public static EshopProductInfo createUnaviable() {
        return new EshopProductInfo() {
            @Override
            public ProductAction getAction() {
                return ProductAction.UNKNOWN;
            }

            @Override
            public Date getActionValidTo() {
                return null;
            }

            @Override
            public BigDecimal getPriceForPackage() {
                return PRICE_UNAVIABLE;
            }

            @Override
            public BigDecimal getPriceForOneItemInPackage() {
                return PRICE_UNAVIABLE;
            }

            @Override
            public BigDecimal getPriceForUnit() {
                return PRICE_UNAVIABLE;
            }
        };
    }
}