package sk.hudak.pricecomparator.middle.to.internal;

import java.io.Serializable;

/**
 * Created by hudak on 18.04.2016.
 */
public class ProductByUrlAnalyzatorRequestDto implements Serializable {

    public static final String AT_PRODUCT_URL = "productUrl";

    private String productUrl;

    public ProductByUrlAnalyzatorRequestDto() {
    }

    public ProductByUrlAnalyzatorRequestDto(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }
}
