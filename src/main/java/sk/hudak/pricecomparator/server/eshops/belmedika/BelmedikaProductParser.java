package sk.hudak.pricecomparator.server.eshops.belmedika;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.canonical.ProductAction;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractEshopProductParserNg;
import sk.hudak.pricecomparator.server.async.ng.impl.ParserUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 20. 8. 2016.
 */
public class BelmedikaProductParser extends AbstractEshopProductParserNg {
    @Override
    protected boolean isProductUnavailable(Document document) {
        return ParserUtils.notExistElement(document, "div[class=ProductButtons] button span");
    }

    @Override
    protected BigDecimal parsePriceForPackage(Document document) {
        Elements elements = document.select("dd[class=price]");
        if (elements.isEmpty()) {
            //TODO vynimka
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
