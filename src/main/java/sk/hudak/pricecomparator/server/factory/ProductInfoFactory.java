package sk.hudak.pricecomparator.server.factory;

import sk.hudak.pricecomparator.middle.model.EshopProductInfo;
import sk.hudak.pricecomparator.middle.model.ProductAction;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 14. 10. 2015.
 */
public class ProductInfoFactory {

    //TODO presunut do api
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

            @Override
            public String getProductImageUrl() {
                return null;
            }

            @Override
            public String getProductNameInEhop() {
                return null;
            }
        };
    }
}
