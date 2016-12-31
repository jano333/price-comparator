package sk.hudak.pricecomparator.middle.to;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.to.NewProductStatus;

import java.io.Serializable;

/**
 * Created by jan on 31. 12. 2016.
 */
public class NewProductListDto implements Serializable {

    private Long id;
    private String productName;
    private String productUrl;
    private String productPictureUrl;
    private NewProductStatus status;
    private EshopType eshopType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getProductPictureUrl() {
        return productPictureUrl;
    }

    public void setProductPictureUrl(String productPictureUrl) {
        this.productPictureUrl = productPictureUrl;
    }

    public NewProductStatus getStatus() {
        return status;
    }

    public void setStatus(NewProductStatus status) {
        this.status = status;
    }

    public EshopType getEshopType() {
        return eshopType;
    }

    public void setEshopType(EshopType eshopType) {
        this.eshopType = eshopType;
    }
}
