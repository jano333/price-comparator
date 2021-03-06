package sk.hudak.pricecomparator.server.eshops.internetovalekaren;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.canonical.ProductAction;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractEshopProductParser;
import sk.hudak.pricecomparator.server.async.ng.impl.ParserUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 23. 8. 2016.
 */
public class InternetovaLekarenProductParser extends AbstractEshopProductParser {

    @Override
    protected boolean isProductUnavailable(Document document) {
        return ParserUtils.notExistElement(document, "button[class=addToCartBtn btn btn-big radius plastic wood]");
    }

    @Override
    protected BigDecimal parsePriceForPackage(Document document) {
        Elements elements = document.select("strong[itemprop=price]");
        StringBuffer sb = new StringBuffer(elements.get(0).text());
        sb = sb.deleteCharAt(sb.length() - 1);
        final String cenaZaBalenie = sb.toString().replace(",", ".");

        return new BigDecimal(cenaZaBalenie);
    }

    @Override
    protected ProductAction parseAction(Document document) {
        // ak je cervenym tak je v akcii, inak nie
        return ParserUtils.existElement(document, "strong[class=fs-xxlarge red]")
                ? ProductAction.IN_ACTION
                : ProductAction.NON_ACTION;
    }

    @Override
    protected Date parseActionValidity(Document document) {
        return ACTION_VALIDITY_NOT_DEFINE;
    }

    @Override
    protected String parseProductName(Document document) {
        //TODO
        return null;
    }
}
