package sk.hudak.pricecomparator.server.xml.model;

import sk.hudak.pricecomparator.middle.api.canonical.Unit;

import java.math.BigDecimal;

/**
 * Created by jan on 14. 10. 2015.
 */
public class ProductXmlEntity {

    private Long id;
    // nazov produktu
    private String name;
    // pocet poloziek v baliku
    private int countOfItemInOnePackage;
    // merna jednotka
    private Unit unit;
    // mnozstvo z danej jednotky(napr. sampon - 0.25l, toaletak - 68m, plienky 72ks)
    private BigDecimal countOfUnit;
    // kategoria
    private Long categoryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
