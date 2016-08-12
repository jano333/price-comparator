package sk.hudak.pricecomparator.server.model;

import sk.hudak.pricecomparator.middle.canonical.ProductAction;

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

    //TODO
    // urobit generovanie podla http://stackoverflow.com/questions/27314165/generate-ddl-script-at-maven-build-with-hibernate4-jpa-2-1

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "PRODUCT_IN_ESHOP_SEQ")
    @SequenceGenerator(name = "PRODUCT_IN_ESHOP_SEQ", sequenceName = "PRODUCT_IN_ESHOP_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PRODUCT_ID")
    private ProductEntity product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ESHOP_ID")
    private EshopEntity eshop;

    // definuje stranku produktu v danom eshope
    @Column(name = "PRODUCT_PAGE_IN_ESHOP", nullable = false, unique = true)
    private String productPageInEshop;

    @Column(name = "PRODUCT_NAME_IN_ESHOP")
    private String productNameInEhop;

    @Column(name = "PRODUCT_ACTION")
    @Enumerated(EnumType.STRING)
    private ProductAction productAction;

    @Column(name = "ACTION_VALID_TO")
    private Date actionValidTo;

    @Column(name = "PRICE_FOR_PACKAGE")
    private BigDecimal priceForPackage;

    @Column(name = "PRICE_FOR_ONE_ITEM_IN_PAC")
    private BigDecimal priceForOneItemInPackage;

    @Column(name = "PRICE_FOR_UNIT")
    private BigDecimal priceForUnit;

    @Column(name = "LAST_UPDATED_PRICE")
    private Date lastUpdatedPrice;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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
}
