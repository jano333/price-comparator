package sk.hudak.pricecomparator.middle.to;

import sk.hudak.pricecomparator.middle.canonical.Unit;

import java.math.BigDecimal;

/**
 * Created by hudak on 25.2.2016.
 */
public class ProductAnalyzatorDto {

    private Unit unit;
    private BigDecimal countOfUnit;
    private Integer countOfItemInPackage;

    public BigDecimal getCountOfUnit() {
        return countOfUnit;
    }

    public ProductAnalyzatorDto setCountOfUnit(BigDecimal countOfUnit) {
        this.countOfUnit = countOfUnit;
        return this;
    }

    public Unit getUnit() {
        return unit;
    }

    public ProductAnalyzatorDto setUnit(Unit unit) {
        this.unit = unit;
        return this;
    }

    public Integer getCountOfItemInPackage() {
        return countOfItemInPackage;
    }

    public ProductAnalyzatorDto setCountOfItemInPackage(Integer countOfItemInPackage) {
        this.countOfItemInPackage = countOfItemInPackage;
        return this;
    }

    @Override
    public String toString() {
        return "ProductAnalyzatorDto{" +
                "unit=" + unit +
                ", countOfUnit=" + countOfUnit +
                ", countOfItemInPackage=" + countOfItemInPackage +
                '}';
    }
}
