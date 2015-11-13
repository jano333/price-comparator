package sk.hudak.pricecomparator.middle.api;

import sk.hudak.pricecomparator.middle.api.canonical.ParserInputData;
import sk.hudak.pricecomparator.middle.api.model.EshopProductInfo;

/**
 * Created by jan on 13. 10. 2015.
 */
public interface EshopProductParser {

    EshopProductInfo getProductInfo(ParserInputData parserInputData);

}
