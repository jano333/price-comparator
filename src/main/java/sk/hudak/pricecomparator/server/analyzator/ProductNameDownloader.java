package sk.hudak.pricecomparator.server.analyzator;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.middle.exeption.PriceComparatorException;
import sk.hudak.pricecomparator.server.async.ng.EshopParserRequest;
import sk.hudak.pricecomparator.server.async.ng.EshopParserResponse;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParser;
import sk.hudak.pricecomparator.server.eshops.tesco.TescoProductParser;

import javax.inject.Named;

/**
 * Created by jan on 24. 4. 2016.
 */
@Named
public class ProductNameDownloader {

    public String downloadProductName(EshopType eshopType, String productUrl) {
        EshopProductParser parser = getParser(eshopType);
        //TODO toto nie je testnute... a cele toto prerobit
        EshopParserRequest request = new EshopParserRequest();
        request.setEshopProductPage(productUrl);
        EshopParserResponse eshopParserResponse = parser.parseEshopProductInfo(request);
        return eshopParserResponse.getProductName();
    }

    private EshopProductParser getParser(EshopType eshopType) {
        switch (eshopType) {
            case TESCO:
                return new TescoProductParser();
            //TODO impl ostatne
            default:
                throw new PriceComparatorException("Neimplementovane pre " + eshopType);
        }
    }
}
