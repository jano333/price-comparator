package sk.hudak.pricecomparator.server.database.model;

import sk.hudak.pricecomparator.middle.api.canonical.Unit;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by jan on 30. 11. 2015.
 */
@Entity
@Table(name = "PRODUCT")
public class ProductEntity extends BasicEntity {

    public static final transient String AT_NAME = "name";

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "PRODUCT_SEQ")
    @SequenceGenerator(name = "PRODUCT_SEQ", sequenceName = "PRODUCT_SEQ", allocationSize = 1)
    private Long id;

    // nazov
    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    // pocet poloziek v baliku
    @Column(name = "COUNT_OF_ITEM_IN_ONE_PACKAGE", nullable = false)
    private int countOfItemInOnePackage;

    // merna jednotka
    @Column(name = "UNIT", nullable = false)
    @Enumerated(EnumType.STRING)
    private Unit unit;

    // mnozstvo z danej jednotky(napr. sampon - 0.25l, toaletak - 68m, plienky 72ks)
    @Column(name = "COUNT_OF_UNIT", nullable = false)
    private BigDecimal countOfUnit;

    // kategoria pod ktoru patri dany produkt
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "CATEGORY_ID")
    private CategoryEntity category;

    // kde je ulozeny obrazok pre dany produkt
    @Column(name = "IMAGE_PATH")
    private String imagePath;

    // definuje co je dobra cena produktu
    @Column(name = "GOOD_PRICE")
    private BigDecimal goodPrice;


    @Override
    public Long getId() {
        return id;
    }

    @Override
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

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public BigDecimal getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(BigDecimal goodPrice) {
        this.goodPrice = goodPrice;
    }
}
