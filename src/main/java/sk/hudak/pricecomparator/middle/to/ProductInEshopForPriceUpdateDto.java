package sk.hudak.pricecomparator.middle.to;

import sk.hudak.pricecomparator.middle.canonical.Unit;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by jan on 14. 11. 2016.
 */
public class ProductInEshopForPriceUpdateDto implements Serializable {

    private Long id;
    private Long productId;
    private int countOfItemInOnePackage;
    private Unit unit;
    private BigDecimal countOfUnit;
    private String eshopProductPage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getCountOfItemInOnePackage() {
        return countOfItemInOnePackage;
    }

    public void setCountOfItemInOnePackage(int countOfItemInOnePackage) {
        this.countOfItemInOnePackage = countOfItemInOnePackage;
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

    public String getEshopProductPage() {
        return eshopProductPage;
    }

    public void setEshopProductPage(String eshopProductPage) {
        this.eshopProductPage = eshopProductPage;
    }
}
