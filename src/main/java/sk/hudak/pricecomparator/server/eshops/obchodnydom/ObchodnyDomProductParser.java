package sk.hudak.pricecomparator.server.eshops.obchodnydom;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.canonical.ProductAction;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractEshopProductParserNg;
import sk.hudak.pricecomparator.server.async.ng.impl.ParserUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 15. 9. 2016.
 */
public class ObchodnyDomProductParser extends AbstractEshopProductParserNg {

    @Override
    protected boolean isProductUnavailable(Document document) {
        return ParserUtils.notExistElement(document, "form#price3 input[type=\"submit\"]");
    }

    @Override
    protected BigDecimal parsePriceForPackage(Document document) {
        //najprv akciovu cenu ak existuje
        Elements elements = document.select("div#akcni strong span");
        String text = null;
        if (elements.isEmpty()) {
            // bezna cena
            elements = document.select("div#cenaa strong");
            text = elements.get(0).text();
            text = ParserUtils.removeLastCharacters(text, 2);
        } else {
            text = elements.get(0).text();
        }
        text = ParserUtils.replaceAllCommaForDot(text);
        return new BigDecimal(text);
    }

    @Override
    protected ProductAction parseAction(Document document) {
        return ParserUtils.existElement(document, "div#akcni strong span")
                ? ProductAction.IN_ACTION
                : ProductAction.NON_ACTION;
    }

    @Override
    protected Date parseActionValidity(Document document) {
        return ACTION_VALIDITY_NOT_DEFINE;
    }

    @Override
    protected String parseProductName(Document document) {
        return null;
    }
}
