package sk.hudak.pricecomparator.server.async.ng;

import sk.hudak.pricecomparator.middle.canonical.ProductAction;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 9. 7. 2016.
 */
@Deprecated
public class EshopParserResponse {

    private BigDecimal priceForUnit;
    private BigDecimal priceForPackage;
    private BigDecimal priceForOneItemInPackage;

    private ProductAction action;
    private Date actionValidTo;

    private String productName;
    private String pictureUrl;

    public BigDecimal getPriceForUnit() {
        return priceForUnit;
    }

    public EshopParserResponse setPriceForUnit(BigDecimal priceForUnit) {
        this.priceForUnit = priceForUnit;
        return this;
    }

    public BigDecimal getPriceForPackage() {
        return priceForPackage;
    }

    public EshopParserResponse setPriceForPackage(BigDecimal priceForPackage) {
        this.priceForPackage = priceForPackage;
        return this;
    }

    public BigDecimal getPriceForOneItemInPackage() {
        return priceForOneItemInPackage;
    }

    public EshopParserResponse setPriceForOneItemInPackage(BigDecimal priceForOneItemInPackage) {
        this.priceForOneItemInPackage = priceForOneItemInPackage;
        return this;
    }

    public ProductAction getAction() {
        return action;
    }

    public EshopParserResponse setAction(ProductAction action) {
        this.action = action;
        return this;
    }

    public Date getActionValidTo() {
        return actionValidTo;
    }

    public EshopParserResponse setActionValidTo(Date actionValidTo) {
        this.actionValidTo = actionValidTo;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public EshopParserResponse setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public EshopParserResponse setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
        return this;
    }

    @Override
    public String toString() {
        return "EshopParserResponse{" +
                "priceForUnit=" + priceForUnit +
                ", priceForPackage=" + priceForPackage +
                ", priceForOneItemInPackage=" + priceForOneItemInPackage +
                ", action=" + action +
                ", actionValidTo=" + actionValidTo +
                ", productName='" + productName + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                '}';
    }
}
