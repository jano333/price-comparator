package sk.hudak.pricecomparator.middle.to.internal;

import sk.hudak.pricecomparator.middle.canonical.Unit;

import java.math.BigDecimal;

/**
 * Created by hudak on 25.2.2016.
 */
public class ProductAnalyzatorResultDto {

    private Unit unit;
    private BigDecimal countOfUnit;
    private Integer countOfItemInPackage;

    public BigDecimal getCountOfUnit() {
        return countOfUnit;
    }

    public ProductAnalyzatorResultDto setCountOfUnit(BigDecimal countOfUnit) {
        this.countOfUnit = countOfUnit;
        return this;
    }

    public Unit getUnit() {
        return unit;
    }

    public ProductAnalyzatorResultDto setUnit(Unit unit) {
        this.unit = unit;
        return this;
    }

    public Integer getCountOfItemInPackage() {
        return countOfItemInPackage;
    }

    public ProductAnalyzatorResultDto setCountOfItemInPackage(Integer countOfItemInPackage) {
        this.countOfItemInPackage = countOfItemInPackage;
        return this;
    }

    @Override
    public String toString() {
        return "ProductAnalyzatorResultDto{" +
                "unit=" + unit +
                ", countOfUnit=" + countOfUnit +
                ", countOfItemInPackage=" + countOfItemInPackage +
                '}';
    }
}
