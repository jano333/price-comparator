package sk.hudak.pricecomparator.server.analyzator;

import org.apache.commons.lang3.StringUtils;
import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.middle.exeption.PriceComparatorBusinesException;
import sk.hudak.pricecomparator.middle.exeption.PriceComparatorException;
import sk.hudak.pricecomparator.middle.to.internal.ProductAnalyzatorResultDto;
import sk.hudak.pricecomparator.middle.to.internal.ProductByUrlAnalyzatorResponseDto;
import sk.hudak.pricecomparator.middle.to.internal.ProductByUrlRequestDto;
import sk.hudak.pricecomparator.server.dao.ProductInEshopDao;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by hudak on 18.04.2016.
 */
@Named
public class ProductByUrlAnalyzator {

    @Inject
    private ProductInEshopDao productInEshopDao;

    @Inject
    private EshopTypeResolver eshopTypeResolver;

    @Inject
    private ProductNameDownloader productNameDownloader;

    @Inject
    private ProductInEshopCreateAnalyzator productDataAnalyzator;

    public ProductByUrlAnalyzatorResponseDto process(ProductByUrlRequestDto requestDto) throws PriceComparatorBusinesException {
        // validacia
        if (requestDto == null) {
            throw new PriceComparatorException("request is null");
        }
        String productUrl = requestDto.getProductUrl();
        if (StringUtils.isBlank(productUrl)) {
            throw new PriceComparatorException("product url is null or empty");
        }
        // 1. zistenie typu eshopu
        EshopType eshopType = eshopTypeResolver.resolveEshop(productUrl);
        if (eshopType == null) {
            throw new PriceComparatorBusinesException("Nepodporovany typ eshopu");
        }
        // 2. overenie, ci uz produkt s danou url neexistuje
        if (productInEshopDao.existProductWithGivenUrl(productUrl)) {
            throw new PriceComparatorBusinesException("Produkt s danou URL uz existuje.");
        }
        // 3. stiahnutie nazvu produktu z danej url...
        String productName = productNameDownloader.downloadProductName(eshopType, productUrl);
        if (StringUtils.isBlank(productName)) {
            throw new PriceComparatorBusinesException("Nepodarilo sa stiahnut nazov produktu.");
        }
        // 4. analyza atributov
        ProductAnalyzatorResultDto productAnalyzatorResultDto = productDataAnalyzator.analyzeFromName(productName);

        // 5. vyskladanie odopovede
        ProductByUrlAnalyzatorResponseDto responseDto = new ProductByUrlAnalyzatorResponseDto();
        responseDto.setProductUrl(productUrl);
        responseDto.setEshopType(eshopType);
        responseDto.setProductName(productName);
        responseDto.setUnit(productAnalyzatorResultDto.getUnit());
        responseDto.setCountOfUnit(productAnalyzatorResultDto.getCountOfUnit());
        responseDto.setCountOfItemInPackage(productAnalyzatorResultDto.getCountOfItemInPackage());
        return responseDto;
    }

}
