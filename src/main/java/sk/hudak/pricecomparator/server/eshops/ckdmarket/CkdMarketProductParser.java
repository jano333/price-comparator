package sk.hudak.pricecomparator.server.eshops.ckdmarket;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.canonical.ProductAction;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractEshopProductParser;
import sk.hudak.pricecomparator.server.async.ng.impl.ParserUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 31. 8. 2016.
 */
public class CkdMarketProductParser extends AbstractEshopProductParser {

    @Override
    protected boolean isProductUnavailable(Document document) {
        return ParserUtils.notExistElement(document, "button[class=submit submitBig submitCart]");
    }

    @Override
    protected BigDecimal parsePriceForPackage(Document document) {
        Elements elements = document.select("span[class=priceTaxValueNumber]");
        if (elements.isEmpty()) {
            return null;
        }
        String text = elements.get(0).text();
        return new BigDecimal(ParserUtils.removeLastCharacters(ParserUtils.replaceAllCommaForDot(text), 2));
    }

    @Override
    protected String parseProductName(Document document) {
        return null;
    }

    @Override
    protected ProductAction parseAction(Document document) {
        return null;
    }

    @Override
    protected Date parseActionValidity(Document document) {
        return null;
    }
}
