package sk.hudak.pricecomparator.middle.api.to;

import sk.hudak.pricecomparator.middle.api.canonical.Unit;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by jan on 14. 10. 2015.
 */
public class ProductCreateDto implements Serializable {

    private String name;
    private int countOfItemInOnePackage;
    private Unit unit;
    private BigDecimal countOfUnit;
    private Long categoryId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
