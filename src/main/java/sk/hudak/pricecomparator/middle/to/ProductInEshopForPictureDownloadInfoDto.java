package sk.hudak.pricecomparator.middle.to;

/**
 * Created by jan on 28. 2. 2016.
 */
public class ProductInEshopForPictureDownloadInfoDto

{
    private Long productId;
    private String productInEshopUrl;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductInEshopUrl() {
        return productInEshopUrl;
    }

    public void setProductInEshopUrl(String productInEshopUrl) {
        this.productInEshopUrl = productInEshopUrl;
    }
}
