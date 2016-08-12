package sk.hudak.pricecomparator.middle.to.internal;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.middle.canonical.Unit;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by jan on 13. 5. 2016.
 */
public class StepTwoRequestDto implements Serializable {

    public static final String AT_PRODUCT_URL = "productUrl";
    public static final String AT_ESHOP_TYPE = "eshopType";
    public static final String AT_PRODUCT_NAME = "productName";
    public static final String AT_UNIT = "unit";
    public static final String AT_COUNT_OF_UNIT = "countOfUnit";
    public static final String AT_COUNT_OF_ITEM_IN_ONE_PACKAGE = "countOfItemInPackage";

    private String productUrl;
    private EshopType eshopType;
    private String productName;

    //null zname sa nepodarilo nast... resp. identifikovat...
    private Integer countOfItemInPackage;
    private Unit unit;
    private BigDecimal countOfUnit;

    public EshopType getEshopType() {
        return eshopType;
    }

    public void setEshopType(EshopType eshopType) {
        this.eshopType = eshopType;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getCountOfItemInPackage() {
        return countOfItemInPackage;
    }

    public void setCountOfItemInPackage(Integer countOfItemInPackage) {
        this.countOfItemInPackage = countOfItemInPackage;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public BigDecimal getCountOfUnit() {
        return countOfUnit;
    }

    public void setCountOfUnit(BigDecimal countOfUnit) {
        this.countOfUnit = countOfUnit;
    }
}
