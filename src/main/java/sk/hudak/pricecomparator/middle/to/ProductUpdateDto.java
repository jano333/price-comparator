package sk.hudak.pricecomparator.middle.to;

import sk.hudak.pricecomparator.middle.canonical.Unit;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by jan on 22. 11. 2015.
 */
public class ProductUpdateDto implements Serializable {

    public static final String AT_NAME = "name";
    public static final String AT_UNIT = "unit";
    public static final String AT_COUNT_OF_UNIT = "countOfUnit";
    public static final String AT_COUNT_OF_ITEM_IN_ONE_PACKAGE = "countOfItemInOnePackage";

    private Long id;
    private String name;
    private Unit unit;
    private BigDecimal countOfUnit;
    private Integer countOfItemInOnePackage;

    //nepouzivaju sa...
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

    public Integer getCountOfItemInOnePackage() {
        return countOfItemInOnePackage;
    }

    public void setCountOfItemInOnePackage(Integer countOfItemInOnePackage) {
        this.countOfItemInOnePackage = countOfItemInOnePackage;
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
