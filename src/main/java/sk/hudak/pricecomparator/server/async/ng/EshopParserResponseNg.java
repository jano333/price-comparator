package sk.hudak.pricecomparator.server.async.ng;

import sk.hudak.pricecomparator.middle.model.ProductAction;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 9. 7. 2016.
 */
public class EshopParserResponseNg {

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

    public EshopParserResponseNg setPriceForUnit(BigDecimal priceForUnit) {
        this.priceForUnit = priceForUnit;
        return this;
    }

    public BigDecimal getPriceForPackage() {
        return priceForPackage;
    }

    public EshopParserResponseNg setPriceForPackage(BigDecimal priceForPackage) {
        this.priceForPackage = priceForPackage;
        return this;
    }

    public BigDecimal getPriceForOneItemInPackage() {
        return priceForOneItemInPackage;
    }

    public EshopParserResponseNg setPriceForOneItemInPackage(BigDecimal priceForOneItemInPackage) {
        this.priceForOneItemInPackage = priceForOneItemInPackage;
        return this;
    }

    public ProductAction getAction() {
        return action;
    }

    public EshopParserResponseNg setAction(ProductAction action) {
        this.action = action;
        return this;
    }

    public Date getActionValidTo() {
        return actionValidTo;
    }

    public EshopParserResponseNg setActionValidTo(Date actionValidTo) {
        this.actionValidTo = actionValidTo;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public EshopParserResponseNg setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public EshopParserResponseNg setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
        return this;
    }
}
