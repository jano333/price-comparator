package sk.hudak.pricecomparator.server.eshops.gigalekarna;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.canonical.ProductAction;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractEshopProductParser;
import sk.hudak.pricecomparator.server.async.ng.impl.ParserUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 17. 7. 2016.
 */
public class GigaLekarnaProductParser extends AbstractEshopProductParser {

    @Override
    protected boolean isProductUnavailable(Document document) {
        return ParserUtils.notExistElement(document, "input[class=buy_detail]");
    }

    @Override
    protected BigDecimal parsePriceForPackage(Document document) {
        Elements elements = document.select("strong[itemprop=price]");
        //dava Kcs nie EUR treba zistit ako to volat tak aby bolo EUR
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
