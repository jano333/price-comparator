package sk.hudak.pricecomparator.server.eshops.esodregeria;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.canonical.ProductAction;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractEshopProductParser;
import sk.hudak.pricecomparator.server.async.ng.impl.ParserUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 2. 9. 2016.
 */
public class EsoDrogeriaProductParser extends AbstractEshopProductParser {

    @Override
    protected boolean isProductUnavailable(Document document) {
        return ParserUtils.notExistElement(document, "input[id=buy_btn]");
    }

    @Override
    protected BigDecimal parsePriceForPackage(Document document) {
        Elements elements = document.select("span[class=price-value def_color]");
        if (elements.isEmpty()) {
            return null;
        }
        String text = elements.get(0).text();
        //15,99&nbsp;EUR
        return new BigDecimal(ParserUtils.removeLastCharacters(ParserUtils.replaceAllCommaForDot(text), 4));
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
