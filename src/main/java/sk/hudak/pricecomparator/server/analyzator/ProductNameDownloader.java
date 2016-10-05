package sk.hudak.pricecomparator.server.analyzator;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.middle.exeption.PriceComparatorException;
import sk.hudak.pricecomparator.server.async.ng.EshopParserRequestNg;
import sk.hudak.pricecomparator.server.async.ng.EshopParserResponseNg;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParserNg;
import sk.hudak.pricecomparator.server.eshops.tesco.TescoProductParser;

import javax.inject.Named;

/**
 * Created by jan on 24. 4. 2016.
 */
@Named
public class ProductNameDownloader {

    public String downloadProductName(EshopType eshopType, String productUrl) {
        EshopProductParserNg parser = getParser(eshopType);
        //TODO toto nie je testnute... a cele toto prerobit
        EshopParserRequestNg request = new EshopParserRequestNg();
        request.setEshopProductPage(productUrl);
        EshopParserResponseNg eshopParserResponseNg = parser.parseEshopProductInfo(request);
        return eshopParserResponseNg.getProductName();
    }

    private EshopProductParserNg getParser(EshopType eshopType) {
        switch (eshopType) {
            case TESCO:
                return new TescoProductParser();
            //TODO impl ostatne
            default:
                throw new PriceComparatorException("Neimplementovane pre " + eshopType);
        }
    }
}
