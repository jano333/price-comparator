package sk.hudak.pricecomparator.server.downloader;

import sk.hudak.pricecomparator.middle.api.model.EshopProductInfo;

/**
 * Created by jan on 7. 11. 2015.
 */
@Deprecated
public class EshopProductPriceDto {
    private Long eshopId;
    private String eshopName;
    private String productEshopPage;
    private EshopProductInfo eshopProductInfo;

    public EshopProductPriceDto(Long eshopId, String eshopName, String productEshopPage, EshopProductInfo eshopProductInfo) {
        this.eshopId = eshopId;
        this.eshopName = eshopName;
        this.productEshopPage = productEshopPage;
        this.eshopProductInfo = eshopProductInfo;
    }

    public Long getEshopId() {
        return eshopId;
    }

    public String getEshopName() {
        return eshopName;
    }

    public EshopProductInfo getEshopProductInfo() {
        return eshopProductInfo;
    }

    public String getProductEshopPage() {
        return productEshopPage;
    }
}
