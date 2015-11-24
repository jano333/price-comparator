package sk.hudak.pricecomparator.server.xml.model;

import sk.hudak.pricecomparator.middle.api.model.ProductAction;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 24. 11. 2015.
 */
public class ProductInEshopPriceXmlEntity {

    private Long id;
    // ku ktoremu produktu je dana cena
    private Long productInEshopId;

    // datum poslednej aktualizacie
    private Date lastCheckedPrice;

    // tieto sa dopocitvaju
    private BigDecimal priceForPackage;
    private BigDecimal priceForOneItemInPackage;
    private BigDecimal priceForUnit;
    private ProductAction productAction;
    private Date actionValidTo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductInEshopId() {
        return productInEshopId;
    }

    public void setProductInEshopId(Long productInEshopId) {
        this.productInEshopId = productInEshopId;
    }

    public Date getLastCheckedPrice() {
        return lastCheckedPrice;
    }

    public void setLastCheckedPrice(Date lastCheckedPrice) {
        this.lastCheckedPrice = lastCheckedPrice;
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
}
