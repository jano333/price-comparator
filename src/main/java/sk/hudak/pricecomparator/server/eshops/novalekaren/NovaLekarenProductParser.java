package sk.hudak.pricecomparator.server.eshops.novalekaren;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.model.ProductAction;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractEshopProductParserNg;
import sk.hudak.pricecomparator.server.async.ng.impl.ParserUtils;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

/**
 * Created by jan on 18. 7. 2016.
 */
public class NovaLekarenProductParser extends AbstractEshopProductParserNg {

    @Override
    protected Map<String, String> getCookies() {
        return Collections.singletonMap("jazykx", "sk");
    }

    @Override
    protected boolean isProductUnavailable(Document document) {
        return ParserUtils.notExistElement(document, "input[class=subimg]");
    }

    @Override
    protected BigDecimal parsePriceForPackage(Document document) {
        Elements elements = document.select("font[color=#00aeff] b");
        if (elements.isEmpty()) {
            return null;
        }
        String text = elements.get(0).text();
        String tmp = ParserUtils.removeLastCharacters(ParserUtils.replaceAllCommaForDot(text), 2);
        return new BigDecimal(tmp);
    }

    @Override
    protected String parseProductName(Document document) {
        //div[id="product_text"] h1
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
