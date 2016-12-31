package sk.hudak.pricecomparator.middle.to.productineshop;

import sk.hudak.pricecomparator.middle.canonical.ProductAction;
import sk.hudak.pricecomparator.middle.canonical.Unit;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 8. 2. 2016.
 */
public class ProductInEshopPriceInfoListDto implements Serializable {

    public static final String AT_PRODUCT_NAME = "productName";
    public static final String AT_UNIT = "unit";
    public static final String AT_PRICE_FOR_UNIT = "priceForUnit";
    public static final String AT_PRICE_FOR_PACKAGE = "priceForPackage";
    public static final String AT_PRICE_FOR_ONE_ITEM_IN_PACKAGE = "priceForOneItemInPackage";
    public static final String AT_PRODUCT_ACTION = "productAction";
    public static final String AT_ACTION_VALID_TO = "actionValidTo";
    public static final String AT_LAST_UPDATED_PRICE = "lastUpdatedPrice";
    public static final String AT_PRODUCT_ESHOP_PAGE = "productEshopPage";

    // product in eshop
    private Long id;
    private String pictureFullPath;
    //    private String eshopName;
    private String productName;

    private Unit unit;
    private BigDecimal priceForUnit;
    private BigDecimal priceForPackage;
    private BigDecimal priceForOneItemInPackage;
    private ProductAction productAction;
    private Date actionValidTo;
    private Date lastUpdatedPrice;

    private String productEshopPage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPictureFullPath() {
        return pictureFullPath;
    }

    public void setPictureFullPath(String pictureFullPath) {
        this.pictureFullPath = pictureFullPath;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
}
