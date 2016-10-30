package sk.hudak.pricecomparator.middle.to;

import sk.hudak.pricecomparator.middle.canonical.ProductAction;
import sk.hudak.pricecomparator.middle.canonical.Unit;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Pouzivane na zobrazenie vyslednej informacie pre porovnavanie.
 * <p/>
 * Created by jan on 19. 1. 2016.
 */
public class ProductInEshopPriceResultListDto implements Serializable {

    public static final String AT_ESHOP_NAME = "eshop." + EshopIdNameDto.AT_NAME;
    public static final String AT_PRODUCT_NAME = "product." + ProductIdNameDto.AT_NAME;

    public static final String AT_UNIT = "unit";
    public static final String AT_PRICE_FOR_UNIT = "priceForUnit";
    public static final String AT_PRICE_FOR_PACKAGE = "priceForPackage";
    public static final String AT_PRICE_FOR_ONE_ITEM_IN_PACKAGE = "priceForOneItemInPackage";
    public static final String AT_PRODUCT_ACTION = "productAction";
    public static final String AT_ACTION_VALID_TO = "actionValidTo";
    public static final String AT_LAST_UPDATED_PRICE = "lastUpdatedPrice";
    public static final String AT_PRODUCT_ESHOP_PAGE = "productEshopPage";


    private Long id;
    private EshopIdNameDto eshop;
    private ProductIdNameDto product;

    private Unit unit;
    private BigDecimal priceForUnit;
    private BigDecimal priceForPackage;
    private BigDecimal priceForOneItemInPackage;
    private ProductAction productAction;
    private Date actionValidTo;
    private Date lastUpdatedPrice;

    private String productEshopPage;
    private String imagePath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EshopIdNameDto getEshop() {
        return eshop;
    }

    public void setEshop(EshopIdNameDto eshop) {
        this.eshop = eshop;
    }

    public ProductIdNameDto getProduct() {
        return product;
    }

    public void setProduct(ProductIdNameDto product) {
        this.product = product;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public BigDecimal getPriceForUnit() {
        return priceForUnit;
    }

    public void setPriceForUnit(BigDecimal priceForUnit) {
        this.priceForUnit = priceForUnit;
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

    public Date getLastUpdatedPrice() {
        return lastUpdatedPrice;
    }

    public void setLastUpdatedPrice(Date lastUpdatedPrice) {
        this.lastUpdatedPrice = lastUpdatedPrice;
    }

    public String getProductEshopPage() {
        return productEshopPage;
    }

    public void setProductEshopPage(String productEshopPage) {
        this.productEshopPage = productEshopPage;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
