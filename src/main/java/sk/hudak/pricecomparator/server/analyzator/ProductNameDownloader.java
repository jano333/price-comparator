package sk.hudak.pricecomparator.server.analyzator;

import sk.hudak.pricecomparator.middle.EshopProductParser;
import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.middle.canonical.ParserInputData;
import sk.hudak.pricecomparator.middle.exeption.PriceComparatorException;
import sk.hudak.pricecomparator.server.eshops.tesco.TescoEshopProductParser;

import javax.inject.Named;

/**
 * Created by jan on 24. 4. 2016.
 */
@Named
public class ProductNameDownloader {

    public String downloadProductName(EshopType eshopType, String productUrl) {
        EshopProductParser parser = getParser(eshopType);
        ParserInputData parserInputData = new ParserInputData(-1, null, null, productUrl);
        return parser.getProductInfo(parserInputData).getProductNameInEhop();
    }

    private EshopProductParser getParser(EshopType eshopType) {
        switch (eshopType) {
            case TESCO:
                return new TescoEshopProductParser();
            //TODO impl ostatne
            default:
                throw new PriceComparatorException("Neimplementovane pre " + eshopType);
        }
    }
}
