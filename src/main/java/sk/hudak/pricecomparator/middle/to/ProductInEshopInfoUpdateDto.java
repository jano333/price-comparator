package sk.hudak.pricecomparator.middle.to;

import sk.hudak.pricecomparator.middle.canonical.ProductAction;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 10. 1. 2016.
 */
public class ProductInEshopInfoUpdateDto implements Serializable {

    // povinne:
    private Long id;
    private BigDecimal priceForPackage;
    private BigDecimal priceForOneItemInPackage;
    private BigDecimal priceForUnit;

    // nepovinne:
    private ProductAction productAction;
    private Date actionValidTo;
    private String productName;
    private String pictureUrl;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductAction getProductAction() {
        return productAction;
    }

    public void setProductAction(ProductAction productAction) {
        this.productAction = productAction;
    }

    public Date getActionValidTo() {
        return actionValidTo;
    }

    public void setActionValidTo(Date actionValidTo) {
        this.actionValidTo = actionValidTo;
    }

    public BigDecimal getPriceForPackage() {
        return priceForPackage;
    }

    public void setPriceForPackage(BigDecimal priceForPackage) {
        this.priceForPackage = priceForPackage;
    }

    public BigDecimal getPriceForOneItemInPackage() {
        return priceForOneItemInPackage;
    }

    public void setPriceForOneItemInPackage(BigDecimal priceForOneItemInPackage) {
        this.priceForOneItemInPackage = priceForOneItemInPackage;
    }

    public BigDecimal getPriceForUnit() {
        return priceForUnit;
    }

    public void setPriceForUnit(BigDecimal priceForUnit) {
        this.priceForUnit = priceForUnit;
    }
}
