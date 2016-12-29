package sk.hudak.pricecomparator.server.to;

/**
 * Created by jan on 27. 12. 2016.
 */
public class NewProductInfoDto {

    private String productName;
    private String productUrl;
    private String productPictureUrl;

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

    public String getProductPictureUrl() {
        return productPictureUrl;
    }

    public void setProductPictureUrl(String productPictureUrl) {
        this.productPictureUrl = productPictureUrl;
    }

    @Override
    public String toString() {
        return "NewProductInfoDto{" +
                "productName='" + productName + '\'' +
                ", productUrl='" + productUrl + '\'' +
                ", productPictureUrl='" + productPictureUrl + '\'' +
                '}';
    }
}
