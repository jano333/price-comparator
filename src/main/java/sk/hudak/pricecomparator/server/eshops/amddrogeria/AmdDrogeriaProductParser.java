package sk.hudak.pricecomparator.server.eshops.amddrogeria;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.canonical.ProductAction;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractEshopProductParser;
import sk.hudak.pricecomparator.server.async.ng.impl.ParserUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 3. 8. 2016.
 */
public class AmdDrogeriaProductParser extends AbstractEshopProductParser {

    @Override
    protected boolean isProductUnavailable(Document document) {
        return ParserUtils.notExistElement(document, "input[class=addtocart-button]");
    }

    @Override
    protected BigDecimal parsePriceForPackage(Document document) {
        Elements elements = document.select("span[class=PricesalesPrice]");
        if (elements.isEmpty()) {
            return null;
        }
        String str = ParserUtils.removeLastCharacters(elements.get(0).text(), 2);
        String replace = str.replace(",", ".");
        return new BigDecimal(replace);
    }

    @Override
    protected String parseProductName(Document document) {
        Elements elements = document.select("div[class=productdetailsin] h1");
        if (elements.isEmpty()) {
            return null;
        }
        String text = elements.get(0).text();
        return text;
    }

    @Override
    protected ProductAction parseAction(Document document) {
        //TODO
        return null;
    }

    @Override
    protected Date parseActionValidity(Document document) {
        //TODO
        return null;
    }
}
