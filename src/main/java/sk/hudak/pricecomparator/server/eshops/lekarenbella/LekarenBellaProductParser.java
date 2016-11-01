package sk.hudak.pricecomparator.server.eshops.lekarenbella;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.canonical.ProductAction;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractEshopProductParser;
import sk.hudak.pricecomparator.server.async.ng.impl.ParserUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 22. 8. 2016.
 */
public class LekarenBellaProductParser extends AbstractEshopProductParser {
    @Override
    protected boolean isProductUnavailable(Document document) {
        return ParserUtils.notExistElement(document, "input[class=koupitbutton]");
    }

    @Override
    protected BigDecimal parsePriceForPackage(Document document) {
        Elements elements = document.select("div[class=cena] span");
        if (elements.isEmpty()) {
            return null;
        }
        String text = elements.get(0).text();
        text = StringUtils.removeStart(text, "Cena: ");
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
