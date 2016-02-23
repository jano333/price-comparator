package sk.hudak.pricecomparator.middle.to;

import sk.hudak.pricecomparator.middle.canonical.Unit;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by jan on 22. 11. 2015.
 */
public class ProductUpdateDto implements Serializable {

    private Long id;
    private String name;
    private int countOfItemInOnePackage;
    private Unit unit;
    private BigDecimal countOfUnit;
    private Long categoryId;

    private String imageLocalPath;
    private byte[] imageContent;

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

    public String getImageLocalPath() {
        return imageLocalPath;
    }

    public void setImageLocalPath(String imageLocalPath) {
        this.imageLocalPath = imageLocalPath;
    }

    public byte[] getImageContent() {
        return imageContent;
    }

    public void setImageContent(byte[] imageContent) {
        this.imageContent = imageContent;
    }
}