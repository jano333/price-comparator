package sk.hudak.pricecomparator.server.eshops.market24;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.canonical.ProductAction;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractEshopProductParser;
import sk.hudak.pricecomparator.server.async.ng.impl.ParserUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 14. 9. 2016.
 */
public class Market24ProductParser extends AbstractEshopProductParser {

    @Override
    protected boolean isProductUnavailable(Document document) {
        //TODO je to rovnake ako ma maderna... tiez toto nefunguje...
        return ParserUtils.notExistElement(document, "input[onclick=\"return js_shopcart_submit(this)\"]");
    }

    @Override
    protected BigDecimal parsePriceForPackage(Document document) {
        Elements select = document.select("span#product-detail-price-value");
        String text = select.get(0).text();
        text = ParserUtils.replaceAllCommaForDot(text);
        text = ParserUtils.removeLastCharacters(text, 2);
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
