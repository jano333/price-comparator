package sk.hudak.pricecomparator.server.factory;

import sk.hudak.pricecomparator.middle.api.model.Eshop;
import sk.hudak.pricecomparator.middle.api.model.Product;
import sk.hudak.pricecomparator.middle.api.model.ProductInEshop;

/**
 * Created by jan on 13. 10. 2015.
 */
public class ProductInEshopFactory {

    public static ProductInEshop createBasic(final Long id,
                                             final Product product,
                                             final Eshop eshop,
                                             final String eshopProductPage) {
        return new ProductInEshop() {
            @Override
            public Long getId() {
                return id;
            }

            @Override
            public Product getProduct() {
                return product;
            }

            @Override
            public Eshop getEshop() {
                return eshop;
            }

            @Override
            public String getEshopProductPage() {
                return eshopProductPage;
            }
        };
    }
}
