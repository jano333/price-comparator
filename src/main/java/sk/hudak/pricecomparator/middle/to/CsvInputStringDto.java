package sk.hudak.pricecomparator.middle.to;

import java.io.Serializable;

/**
 * Created by hudak on 18.2.2016.
 */
public class CsvInputStringDto implements Serializable {

    private String eshopType;
    private String productName;
    private String unit;
    private String countOfUnit;
    private String countOfItems;
    private String urlOfProduct;

    public String getEshopType() {
        return eshopType;
    }

    public void setEshopType(String eshopType) {
        this.eshopType = eshopType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCountOfUnit() {
        return countOfUnit;
    }

    public void setCountOfUnit(String countOfUnit) {
        this.countOfUnit = countOfUnit;
    }

    public String getCountOfItems() {
        return countOfItems;
    }

    public void setCountOfItems(String countOfItems) {
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

        CsvInputStringDto that = (CsvInputStringDto) o;

        if (eshopType != null ? !eshopType.equals(that.eshopType) : that.eshopType != null) return false;
        if (productName != null ? !productName.equals(that.productName) : that.productName != null) return false;
        if (unit != null ? !unit.equals(that.unit) : that.unit != null) return false;
        if (countOfUnit != null ? !countOfUnit.equals(that.countOfUnit) : that.countOfUnit != null) return false;
        if (countOfItems != null ? !countOfItems.equals(that.countOfItems) : that.countOfItems != null) return false;
        return !(urlOfProduct != null ? !urlOfProduct.equals(that.urlOfProduct) : that.urlOfProduct != null);

    }

    @Override
    public int hashCode() {
        int result = eshopType != null ? eshopType.hashCode() : 0;
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        result = 31 * result + (countOfUnit != null ? countOfUnit.hashCode() : 0);
        result = 31 * result + (countOfItems != null ? countOfItems.hashCode() : 0);
        result = 31 * result + (urlOfProduct != null ? urlOfProduct.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CsvInputStringDto{" +
                "eshopType='" + eshopType + '\'' +
                ", productName='" + productName + '\'' +
                ", unit='" + unit + '\'' +
                ", countOfUnit='" + countOfUnit + '\'' +
                ", countOfItems='" + countOfItems + '\'' +
                ", urlOfProduct='" + urlOfProduct + '\'' +
                '}';
    }
}
