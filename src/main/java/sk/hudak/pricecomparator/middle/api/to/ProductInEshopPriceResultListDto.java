package sk.hudak.pricecomparator.middle.api.to;

import sk.hudak.pricecomparator.middle.api.model.ProductAction;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Pouzivane na zobrazenie vyslednej informacie pre porovnavanie.
 * <p/>
 * Created by jan on 19. 1. 2016.
 */
public class ProductInEshopPriceResultListDto implements Serializable {

    private Long id;
    private String productEshopPage;
    private String eshopName;
    private ProductAction productAction;
    private Date actionValidTo;
    private BigDecimal priceForPackage;
    private BigDecimal priceForOneItemInPackage;
    private BigDecimal priceForUnit;
    private Date lastUpdatedPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductEshopPage() {
        return productEshopPage;
    }

    public void setProductEshopPage(String productEshopPage) {
        this.productEshopPage = productEshopPage;
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

    public String getEshopName() {
        return eshopName;
    }

    public void setEshopName(String eshopName) {
        this.eshopName = eshopName;
    }

    public Date getLastUpdatedPrice() {
        return lastUpdatedPrice;
    }

    public void setLastUpdatedPrice(Date lastUpdatedPrice) {
        this.lastUpdatedPrice = lastUpdatedPrice;
    }
}
