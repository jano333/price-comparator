package sk.hudak.pricecomparator.middle.api.model;

/**
 * Created by jan on 13. 10. 2015.
 */
public interface ProductInEshop {

    Long getId();

    Eshop getEshop();

    Product getProduct();

    String getEshopProductPage();

}
