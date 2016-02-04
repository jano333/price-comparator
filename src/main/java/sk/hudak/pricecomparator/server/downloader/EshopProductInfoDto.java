package sk.hudak.pricecomparator.server.downloader;

import sk.hudak.pricecomparator.middle.model.EshopProductInfo;
import sk.hudak.pricecomparator.middle.model.ProductAction;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 12. 11. 2015.
 */
public class EshopProductInfoDto {

    private ProductAction productAction;
    private Date actionValidTo;
    private BigDecimal priceForPackage;
    private BigDecimal priceForOneItemInPackage;
    private BigDecimal priceForUnit;

    private EshopProductInfoDto(ProductAction productAction, Date actionValidTo, BigDecimal priceForPackage,
                                BigDecimal priceForOneItemInPackage, BigDecimal priceForUnit) {
        this.productAction = productAction;
        this.actionValidTo = actionValidTo;
        this.priceForPackage = priceForPackage;
        this.priceForOneItemInPackage = priceForOneItemInPackage;
        this.priceForUnit = priceForUnit;
    }

    public static EshopProductInfoDto create(EshopProductInfo info) {
        return new EshopProductInfoDto(
                info.getAction(),
                info.getActionValidTo(),
                info.getPriceForPackage(),
                info.getPriceForOneItemInPackage(),
                info.getPriceForUnit());
    }
}
