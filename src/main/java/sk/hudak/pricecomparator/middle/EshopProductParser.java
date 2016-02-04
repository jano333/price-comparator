package sk.hudak.pricecomparator.middle;

import sk.hudak.pricecomparator.middle.canonical.ParserInputData;
import sk.hudak.pricecomparator.middle.model.EshopProductInfo;

/**
 * Created by jan on 13. 10. 2015.
 */
public interface EshopProductParser {

    EshopProductInfo getProductInfo(ParserInputData parserInputData);

}
