package sk.hudak.pricecomparator.server.eshops.retrogeria;

import org.jsoup.nodes.Document;
import sk.hudak.pricecomparator.middle.canonical.ProductAction;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractEshopProductParser;
import sk.hudak.pricecomparator.server.async.ng.impl.ParserUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 15. 9. 2016.
 */
public class RetrogeriaProductParser extends AbstractEshopProductParser {
    @Override
    protected boolean isProductUnavailable(Document document) {
        return ParserUtils.notExistElement(document, "input[value=\"Vložiť do košíka\"]");
    }

    @Override
    protected BigDecimal parsePriceForPackage(Document document) {
        String text = document.select("div[class=\"price_detail\"] span").get(0).text();
        text = ParserUtils.removeLastCharacters(text, 2);
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
