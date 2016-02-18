package sk.hudak.pricecomparator.middle.to;

import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.middle.canonical.Unit;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by hudak on 18.2.2016.
 */
public class CsvProductInEshopDto implements Serializable {

    private EshopType eshopType;
    private String productName;
    private Unit unit;
    private BigDecimal countOfUnit;
    private int countOfItems;
    private String urlOfProduct;

    public EshopType getEshopType() {
        return eshopType;
    }

    public void setEshopType(EshopType eshopType) {
        this.eshopType = eshopType;
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

    public int getCountOfItems() {
        return countOfItems;
    }

    public void setCountOfItems(int countOfItems) {
        this.countOfItems = countOfItems;
    }

    public String getUrlOfProduct() {
        return urlOfProduct;
    }

    public void setUrlOfProduct(String urlOfProduct) {
        this.urlOfProduct = urlOfProduct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CsvProductInEshopDto that = (CsvProductInEshopDto) o;

        if (countOfItems != that.countOfItems) return false;
        if (eshopType != that.eshopType) return false;
        if (productName != null ? !productName.equals(that.productName) : that.productName != null) return false;
        if (unit != that.unit) return false;
        if (countOfUnit != null ? !countOfUnit.equals(that.countOfUnit) : that.countOfUnit != null) return false;
        return !(urlOfProduct != null ? !urlOfProduct.equals(that.urlOfProduct) : that.urlOfProduct != null);

    }

    @Override
    public int hashCode() {
        int result = eshopType != null ? eshopType.hashCode() : 0;
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        result = 31 * result + (countOfUnit != null ? countOfUnit.hashCode() : 0);
        result = 31 * result + countOfItems;
        result = 31 * result + (urlOfProduct != null ? urlOfProduct.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CsvProductInEshopDto{" +
                "eshopType=" + eshopType +
                ", productName='" + productName + '\'' +
                ", unit=" + unit +
                ", countOfUnit=" + countOfUnit +
                ", countOfItems=" + countOfItems +
                ", urlOfProduct='" + urlOfProduct + '\'' +
                '}';
    }
}
