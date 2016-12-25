package sk.hudak.pricecomparator.server.to;

/**
 * Created by hudak on 23.12.2016.
 */
public class ProductInEshopPictureDto {

    private Long productInEshopId;
    private String pictureUrl;

    public ProductInEshopPictureDto(Long productInEshopId, String pictureUrl) {
        this.productInEshopId = productInEshopId;
        this.pictureUrl = pictureUrl;
    }

    public Long getProductInEshopId() {
        return productInEshopId;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }
}
