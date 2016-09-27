package sk.hudak.pricecomparator.middle.to;

import java.io.Serializable;

/**
 * Created by jan on 25. 9. 2016.
 */
public class ProductInEshopUpdateDto implements Serializable {

    public static final String AT_ID = "id";
    public static final String AT_ESHOP_ID = "eshopId";
    public static final String AT_PRODUCT_ID = "productId";
    public static final String AT_ESHOP_PRODUCT_PAGE = "eshopProductPage";

    private Long id;
    private Long eshopId;
    private Long productId;
    private String eshopProductPage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEshopId() {
        return eshopId;
    }

    public void setEshopId(Long eshopId) {
        this.eshopId = eshopId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getEshopProductPage() {
        return eshopProductPage;
    }

    public void setEshopProductPage(String eshopProductPage) {
        this.eshopProductPage = eshopProductPage;
    }
}
