package sk.hudak.pricecomparator.middle.to;

import sk.hudak.pricecomparator.middle.canonical.ProductAction;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 10. 1. 2016.
 */
public class ProductInEshopPriceUpdateDto implements Serializable {

    private Long id;
    private ProductAction productAction;
    private Date actionValidTo;
    private BigDecimal priceForPackage;
    private BigDecimal priceForOneItemInPackage;
    private BigDecimal priceForUnit;

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
