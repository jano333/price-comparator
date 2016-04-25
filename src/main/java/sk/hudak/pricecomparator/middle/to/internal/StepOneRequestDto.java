package sk.hudak.pricecomparator.middle.to.internal;

import java.io.Serializable;

/**
 * Created by hudak on 18.04.2016.
 */
public class StepOneRequestDto implements Serializable {

    public static final String AT_PRODUCT_URL = "productUrl";

    private String productUrl;

    public StepOneRequestDto() {
    }

    public StepOneRequestDto(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }
}
