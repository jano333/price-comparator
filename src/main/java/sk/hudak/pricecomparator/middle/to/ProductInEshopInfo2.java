package sk.hudak.pricecomparator.middle.to;

import java.io.Serializable;

/**
 * Created by jan on 11. 6. 2016.
 */
public class ProductInEshopInfo2 implements Serializable {

    private Long productInEshopId;
    private String productInEshopPage;

    public ProductInEshopInfo2() {
    }

    public ProductInEshopInfo2(Long productInEshopId, String productInEshopPage) {
        this.productInEshopId = productInEshopId;
        this.productInEshopPage = productInEshopPage;
    }

    public Long getProductInEshopId() {
        return productInEshopId;
    }

    public void setProductInEshopId(Long productInEshopId) {
        this.productInEshopId = productInEshopId;
    }

    public String getProductInEshopPage() {
        return productInEshopPage;
    }

    public void setProductInEshopPage(String productInEshopPage) {
        this.productInEshopPage = productInEshopPage;
    }

    @Override
    public String toString() {
        return "ProductInEshopInfo2{" +
                "productInEshopId=" + productInEshopId +
                ", productInEshopPage='" + productInEshopPage + '\'' +
                '}';
    }
}
