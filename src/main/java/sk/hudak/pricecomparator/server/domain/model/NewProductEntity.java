package sk.hudak.pricecomparator.server.domain.model;

import sk.hudak.pricecomparator.server.to.NewProductStatus;

/**
 * Created by jan on 27. 12. 2016.
 */
public class NewProductEntity extends BasicEntity {

    private Long id;

    // povinne
    private String productName;

    // povinne
    private String productUrl;

    // nepovinne
    private String productPictureUrl;

    // povinne
    private NewProductStatus status;

    @Override
    public Long getId() {
        return id;
    }

    @Override
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
}
