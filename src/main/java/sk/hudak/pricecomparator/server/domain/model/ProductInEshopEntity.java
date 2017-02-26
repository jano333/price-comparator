package sk.hudak.pricecomparator.server.domain.model;

import sk.hudak.pricecomparator.middle.canonical.ProductAction;
import sk.hudak.pricecomparator.middle.canonical.Unit;
import sk.hudak.pricecomparator.middle.to.internal.ProductInEshopUpdateStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 1. 12. 2015.
 */
@Entity
@Table(name = "PRODUCT_IN_ESHOP")
public class ProductInEshopEntity extends BasicEntity {

    public static final transient String AT_PRODUCT = "product";
    public static final transient String AT_ESHOP = "eshop";
    public static final transient String AT_LAST_UPDATED_PRICE = "lastUpdatedPrice";
    public static final transient String AT_PRICE_FOR_UNIT = "priceForUnit";
    public static final transient String AT_PRODUCT_ACTION = "productAction";
    public static final transient String AT_PRODUCT_PAGE_IN_ESHOP = "productPageInEshop";
    public static final transient String AT_PRODUCT_PICTURE_URL = "productPictureUrl";
    public static final transient String AT_PICTURE_EXIST = "pictureExist";

    //TODO
    // urobit generovanie podla http://stackoverflow.com/questions/27314165/generate-ddl-script-at-maven-build-with-hibernate4-jpa-2-1

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "PRODUCT_IN_ESHOP_SEQ")
    @SequenceGenerator(name = "PRODUCT_IN_ESHOP_SEQ", sequenceName = "PRODUCT_IN_ESHOP_SEQ", allocationSize = 1)
    private Long id;

    // eshop, z ktoreho je dany produkt
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ESHOP_ID")
    private EshopEntity eshop;

    // definuje stranku produktu v danom eshope
    @Column(name = "PRODUCT_PAGE_IN_ESHOP", nullable = false, unique = true)
    private String productPageInEshop;

    // pocet poloziek v baliku
    @Column(name = "COUNT_OF_ITEM_IN_ONE_PACKAGE", nullable = false)
    private Integer countOfItemInOnePackage;

    // merna jednotka
    @Column(name = "UNIT", nullable = false)
    @Enumerated(EnumType.STRING)
    private Unit unit;

    // mnozstvo z danej jednotky(napr. sampon - 0.25l, toaletak - 68m, plienky 72ks)
    @Column(name = "COUNT_OF_UNIT", nullable = false)
    private BigDecimal countOfUnit;

//    // kategoria pod ktoru patri dany produkt
//    @ManyToOne(fetch = FetchType.LAZY, optional = true)
//    @JoinColumn(name = "CATEGORY_ID")
//    private CategoryEntity category;

    // nasledovne atributy sa aktualizuje pri aktualizacii produktu

    // definuje nazov produktu v eshope(TODO kedy sa aktualizuje)
    @Column(name = "PRODUCT_NAME_IN_ESHOP")
    private String productNameInEhop;

    // cena za balenie
    @Column(name = "PRICE_FOR_PACKAGE")
    private BigDecimal priceForPackage;

    // typ akcie
    @Column(name = "PRODUCT_ACTION")
    @Enumerated(EnumType.STRING)
    private ProductAction productAction;

    // platnost akcie
    @Column(name = "ACTION_VALID_TO")
    private Date actionValidTo;

    // url na obrazok produktu
    @Column(name = "PRODUCT_PICTURE_URL")
    private String productPictureUrl;

    @Column(name = "PRICE_FOR_ONE_ITEM_IN_PAC")
    private BigDecimal priceForOneItemInPackage;

    @Column(name = "PRICE_FOR_UNIT")
    private BigDecimal priceForUnit;

    @Column(name = "LAST_UPDATED_PRICE")
    private Date lastUpdatedPrice;

    //TODO bude tu cena za balik? alebo jednotkova cena? asi za balik aby som mal prehlad...
    @Column(name = "BEST_PRICE")
    private BigDecimal bestPrice;

    @Column(name = "UPDATE_STATUS")
    @Enumerated(value = EnumType.STRING)
    private ProductInEshopUpdateStatus updateStatus;

    @Column(name = "PICTURE_EXIST")
    private Boolean pictureExist;

    //TODO bude zrusene...
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PRODUCT_ID")
    private ProductEntity product;


    public Integer getCountOfItemInOnePackage() {
        return countOfItemInOnePackage;
    }

    public void setCountOfItemInOnePackage(Integer countOfItemInOnePackage) {
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

    public Boolean getPictureExist() {
        return pictureExist;
    }

    public void setPictureExist(Boolean pictureExist) {
        this.pictureExist = pictureExist;
    }

    public ProductInEshopUpdateStatus getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(ProductInEshopUpdateStatus updateStatus) {
        this.updateStatus = updateStatus;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }


    public String getProductPictureUrl() {
        return productPictureUrl;
    }

    public void setProductPictureUrl(String productPictureUrl) {
        this.productPictureUrl = productPictureUrl;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public EshopEntity getEshop() {
        return eshop;
    }

    public void setEshop(EshopEntity eshop) {
        this.eshop = eshop;
    }

    public String getProductPageInEshop() {
        return productPageInEshop;
    }

    public void setProductPageInEshop(String productPageInEshop) {
        this.productPageInEshop = productPageInEshop;
    }

    public String getProductNameInEhop() {
        return productNameInEhop;
    }

    public void setProductNameInEhop(String productNameInEhop) {
        this.productNameInEhop = productNameInEhop;
    }

    public ProductAction getProductAction() {
        return productAction;
    }

    public void setProductAction(ProductAction productAction) {
        this.productAction = productAction;
    }

    public Date getActionValidTo() {
        return actionValidTo;
    }

    public void setActionValidTo(Date actionValidTo) {
        this.actionValidTo = actionValidTo;
    }

    public BigDecimal getPriceForPackage() {
        return priceForPackage;
    }

    public void setPriceForPackage(BigDecimal priceForPackage) {
        this.priceForPackage = priceForPackage;
    }

    public BigDecimal getPriceForOneItemInPackage() {
        return priceForOneItemInPackage;
    }

    public void setPriceForOneItemInPackage(BigDecimal priceForOneItemInPackage) {
        this.priceForOneItemInPackage = priceForOneItemInPackage;
    }

    public BigDecimal getPriceForUnit() {
        return priceForUnit;
    }

    public void setPriceForUnit(BigDecimal priceForUnit) {
        this.priceForUnit = priceForUnit;
    }

    public Date getLastUpdatedPrice() {
        return lastUpdatedPrice;
    }

    public void setLastUpdatedPrice(Date lastUpdatedPrice) {
        this.lastUpdatedPrice = lastUpdatedPrice;
    }

    public BigDecimal getBestPrice() {
        return bestPrice;
    }

    public void setBestPrice(BigDecimal bestPrice) {
        this.bestPrice = bestPrice;
    }
}
