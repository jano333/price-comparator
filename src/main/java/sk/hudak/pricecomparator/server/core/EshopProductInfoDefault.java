package sk.hudak.pricecomparator.server.core;

import sk.hudak.pricecomparator.middle.model.EshopProductInfo;
import sk.hudak.pricecomparator.middle.model.ProductAction;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 14. 10. 2015.
 */
@Deprecated
public class EshopProductInfoDefault implements EshopProductInfo {

    private ProductAction action;
    private Date actionTo;
    private BigDecimal priceForPackage;
    private BigDecimal priceForItemInPackage;
    private BigDecimal priceForJednotka;

    public void setAction(ProductAction action) {
        this.action = action;
    }

    public void setActionTo(Date actionTo) {
        this.actionTo = actionTo;
    }

    public void setPriceForPackage(BigDecimal priceForPackage) {
        this.priceForPackage = priceForPackage;
    }

    public void setPriceForItemInPackage(BigDecimal priceForItemInPackage) {
        this.priceForItemInPackage = priceForItemInPackage;
    }

    public void setPriceForJednotka(BigDecimal priceForJednotka) {
        this.priceForJednotka = priceForJednotka;
    }

    @Override
    public ProductAction getAction() {
        return action;
    }

    @Override
    public Date getActionValidTo() {
        return actionTo;
    }

    @Override
    public BigDecimal getPriceForPackage() {
        return priceForPackage;
    }

    @Override
    public BigDecimal getPriceForOneItemInPackage() {
        return priceForItemInPackage;
    }

    @Override
    public BigDecimal getPriceForUnit() {
        return priceForJednotka;
    }

    @Override
    public String getProductImageUrl() {
        //TODO impl
        return null;
    }
}
