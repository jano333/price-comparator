package sk.hudak.pricecomparator.server.eshops.ng.feedo;

/**
 * Created by jan on 27. 12. 2016.
 */
public class NewProductInfoDto {

    private String productName;
    private String productUrl;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    @Override
    public String toString() {
        return "NewProductInfoDto{" +
                "productName='" + productName + '\'' +
                ", productUrl='" + productUrl + '\'' +
                '}';
    }
}
