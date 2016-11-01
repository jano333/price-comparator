package sk.hudak.pricecomparator.server.eshops.shoppie;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.canonical.ProductAction;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractEshopProductParser;
import sk.hudak.pricecomparator.server.async.ng.impl.ParserUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 16. 7. 2016.
 */
public class ShoppieProductParser extends AbstractEshopProductParser {

    @Override
    protected boolean isProductUnavailable(Document document) {
        return ParserUtils.notExistElement(document, "button[id=frm-productInfo-buyBoxForm-addToBasket]");
    }

    @Override
    protected BigDecimal parsePriceForPackage(Document document) {
        Elements elements = document.select("p[class=reset product-price] strong[itemprop=price]");
        if (elements.isEmpty()) {
            return null;
        }
        String text = elements.get(0).text();
        String value = ParserUtils.removeLastCharacters(ParserUtils.replaceAllCommaForDot(text), 2);
        return new BigDecimal(value);
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
