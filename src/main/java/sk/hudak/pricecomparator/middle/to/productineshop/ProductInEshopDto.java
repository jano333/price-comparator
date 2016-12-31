package sk.hudak.pricecomparator.middle.to.productineshop;

import sk.hudak.pricecomparator.middle.to.eshop.EshopIdNameDto;
import sk.hudak.pricecomparator.middle.to.product.ProductIdNameDto;

import java.io.Serializable;

/**
 * Created by jan on 7. 11. 2015.
 */
public class ProductInEshopDto implements Serializable {

    private Long id;
    private ProductIdNameDto productId;
    private EshopIdNameDto eshopId;
    private String eshopProductPage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductIdNameDto getProductId() {
        return productId;
    }

    public void setProductId(ProductIdNameDto productId) {
        this.productId = productId;
    }

    public EshopIdNameDto getEshopId() {
        return eshopId;
    }

    public void setEshopId(EshopIdNameDto eshopId) {
        this.eshopId = eshopId;
    }

    public String getEshopProductPage() {
        return eshopProductPage;
    }

    public void setEshopProductPage(String eshopProductPage) {
        this.eshopProductPage = eshopProductPage;
    }
}
