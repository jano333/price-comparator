package sk.hudak.pricecomparator.server.analyzator;

import org.apache.commons.lang3.StringUtils;
import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.middle.exeption.PriceComparatorBusinesException;
import sk.hudak.pricecomparator.middle.exeption.PriceComparatorException;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.middle.to.ProductAnalyzatorDto;
import sk.hudak.pricecomparator.middle.to.StepOneRequestDto;
import sk.hudak.pricecomparator.middle.to.StepOneResponseDto;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by hudak on 18.04.2016.
 */
@Named
public class StepOneProcessor {

    @Inject
    private EshopTypeResolver eshopTypeResolver;

    @Inject
    private PriceComparatorService service;

    @Inject
    private ProductNameDownloader productNameDownloader;


    public StepOneResponseDto process(StepOneRequestDto requestDto) throws PriceComparatorBusinesException {
        // validacia
        if (requestDto == null) {
            throw new PriceComparatorException("request is null");
        }
        final String productUrl = requestDto.getProductUrl();
        if (StringUtils.isBlank(productUrl)) {
            throw new PriceComparatorException("product url is null or empty");
        }
        // 1. zistenie typu eshopu
        EshopType eshopType = eshopTypeResolver.resolveEshop(productUrl);
        if (eshopType == null) {
            throw new PriceComparatorBusinesException("Nepodporovany typ eshopu");
        }
        // 2. overenie, ci uz produkt s danou url neexistuje
        if (service.existProductWithGivenUrl(productUrl)) {
            throw new PriceComparatorBusinesException("Produkt s danou URL uz existuje.");
        }
        // 3. stiahnutie nazvu produktu z danej url...
        String productName = productNameDownloader.downloadProductName(eshopType, productUrl);

        // 4. analyza atributov
        ProductAnalyzatorDto productAnalyzatorDto = new ProductInEshopCreateAnalyzator().analyzeFromName(productName);

        StepOneResponseDto responseDto = new StepOneResponseDto();
        responseDto.setEshopType(eshopType);
        responseDto.setProductName(productName);
        responseDto.setUnit(productAnalyzatorDto.getUnit());
        responseDto.setCountOfUnit(productAnalyzatorDto.getCountOfUnit());
        responseDto.setCountOfItemInPackage(productAnalyzatorDto.getCountOfItemInPackage());
        return responseDto;
    }

    private boolean existProductWithGivenUrl(String productUrl) {
        //TODO db overenie existencie daneho productu
        return false;
    }

}
