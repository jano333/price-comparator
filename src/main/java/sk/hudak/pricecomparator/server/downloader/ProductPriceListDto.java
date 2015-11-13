package sk.hudak.pricecomparator.server.downloader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jan on 7. 11. 2015.
 */
@Deprecated
public class ProductPriceListDto {

    private Long productId;
    private String productName;
    private List<EshopProductPriceDto> eshopProductPriceDtos = new ArrayList<>();

    public ProductPriceListDto(Long productId, String productName, List<EshopProductPriceDto> eshopProductPriceDtos) {
        this.productId = productId;
        this.productName = productName;
        this.eshopProductPriceDtos = eshopProductPriceDtos;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public List<EshopProductPriceDto> getEshopProductPriceDtos() {
        return eshopProductPriceDtos;
    }
}
