package sk.hudak.pricecomparator.middle.to;

import java.io.Serializable;

/**
 * Created by jan on 14. 10. 2015.
 */
public class ProductInEshopCreateDto implements Serializable {

    public static final String AT_ESHOP_PRODUCT_PAGE = "eshopProductPage";

    private Long productId;
    private Long eshopId;
    private String eshopProductPage;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getEshopId() {
        return eshopId;
    }

    public void setEshopId(Long eshopId) {
        this.eshopId = eshopId;
    }

    public String getEshopProductPage() {
        return eshopProductPage;
    }

    public void setEshopProductPage(String eshopProductPage) {
        this.eshopProductPage = eshopProductPage;
    }
}
