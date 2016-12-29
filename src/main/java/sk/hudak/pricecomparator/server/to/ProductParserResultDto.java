package sk.hudak.pricecomparator.server.to;

import sk.hudak.pricecomparator.middle.canonical.ProductAction;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 3. 11. 2016.
 */
public class ProductParserResultDto {

    private String productPage;
    private BigDecimal productPriceForPackage;
    private ProductAction productAction;
    private Date productActionValidity;
    private String productName;
    private String productPictureURL;

    public String getProductPage() {
        return productPage;
    }

    public void setProductPage(String productPage) {
        this.productPage = productPage;
    }

    public BigDecimal getProductPriceForPackage() {
        return productPriceForPackage;
    }

    public void setProductPriceForPackage(BigDecimal productPriceForPackage) {
        this.productPriceForPackage = productPriceForPackage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ProductAction getProductAction() {
        return productAction;
    }

    public void setProductAction(ProductAction productAction) {
        this.productAction = productAction;
    }

    public Date getProductActionValidity() {
        return productActionValidity;
    }

    public void setProductActionValidity(Date productActionValidity) {
        this.productActionValidity = productActionValidity;
    }

    public String getProductPictureURL() {
        return productPictureURL;
    }

    public void setProductPictureURL(String productPictureURL) {
        this.productPictureURL = productPictureURL;
    }

    @Override
    public String toString() {
        return "ProductParserResultDto{" +
                "productPage='" + productPage + '\'' +
                ", productPriceForPackage=" + productPriceForPackage +
                ", productName='" + productName + '\'' +
                ", productAction=" + productAction +
                ", productActionValidity=" + productActionValidity +
                ", productPictureURL='" + productPictureURL + '\'' +
                '}';
    }
}
