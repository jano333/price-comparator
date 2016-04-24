package sk.hudak.pricecomparator.middle.to;

/**
 * Created by hudak on 18.04.2016.
 */
public class StepOneRequestDto {

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
