package sk.hudak.pricecomparator.server.eshops.mojalekaren;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.canonical.ProductAction;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractEshopProductParser;
import sk.hudak.pricecomparator.server.async.ng.impl.ParserUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 12. 9. 2016.
 */
public class MojalekarenProductParser extends AbstractEshopProductParser {

    @Override
    protected boolean isProductUnavailable(Document document) {
        return ParserUtils.notExistElement(document, "div[class=\"detailProductInformation\"] button[class=\"buy-cz\"]");
    }

    @Override
    protected BigDecimal parsePriceForPackage(Document document) {
        Elements elements = document.select("td[class=\"price\"]");
        String cena = elements.get(0).text();
        cena = ParserUtils.removeLastCharacters(cena, 2);
        cena = ParserUtils.replaceAllCommaForDot(cena);
        return new BigDecimal(cena);
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
