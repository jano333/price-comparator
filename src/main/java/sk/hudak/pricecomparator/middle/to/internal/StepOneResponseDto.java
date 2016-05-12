package sk.hudak.pricecomparator.middle.to.internal;

import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.middle.canonical.Unit;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by hudak on 18.04.2016.
 */
public class StepOneResponseDto implements Serializable {

    public static final String AT_PRODUCT_NAME = "productName";

    public static final String AT_UNIT = "unit";
    public static final String AT_COUNT_OF_UNIT = "countOfUnit";
    public static final String AT_COUNT_OF_ITEM_IN_ONE_PACKAGE = "countOfItemInPackage";

    private EshopType eshopType;
    private String productUrl;
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

    public Integer getCountOfItemInPackage() {
        return countOfItemInPackage;
    }

    public void setCountOfItemInPackage(Integer countOfItemInPackage) {
        this.countOfItemInPackage = countOfItemInPackage;
    }

    @Override
    public String toString() {
        return "StepOneResponseDto{" +
                "eshopType=" + eshopType +
                ", productUrl='" + productUrl + '\'' +
                ", productName='" + productName + '\'' +
                ", unit=" + unit +
                ", countOfUnit=" + countOfUnit +
                ", countOfItemInPackage=" + countOfItemInPackage +
                '}';
    }
}
