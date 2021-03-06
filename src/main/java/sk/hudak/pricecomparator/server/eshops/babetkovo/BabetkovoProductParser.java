package sk.hudak.pricecomparator.server.eshops.babetkovo;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.canonical.ProductAction;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractEshopProductParser;
import sk.hudak.pricecomparator.server.async.ng.impl.ParserUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 5. 8. 2016.
 */
public class BabetkovoProductParser extends AbstractEshopProductParser {

    @Override
    protected boolean isProductUnavailable(Document document) {
        return ParserUtils.notExistElement(document, "input[id=button-cart]");
    }

    @Override
    protected BigDecimal parsePriceForPackage(Document document) {
        Elements elements = document.select("div[class=price]");
        if (elements.isEmpty()) {
            return null;
        }
        String text = elements.get(0).text();
        text = text.substring(0, text.indexOf("€"));
        text = ParserUtils.replaceAllCommaForDot(text);
        return new BigDecimal(text);
    }

    @Override
    protected ProductAction parseAction(Document document) {
        return null;
    }

    @Override
    protected Date parseActionValidity(Document document) {
        return null;
    }

    @Override
    protected String parseProductName(Document document) {
        return null;
    }
}
