package sk.hudak.pricecomparator.server.async.ng;

/**
 * Created by jan on 9. 7. 2016.
 */
public interface EshopProductParser {

    EshopParserResponse parseEshopProductInfo(EshopParserRequest request);
}
