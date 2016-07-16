package sk.hudak.pricecomparator.server.eshops.metro;

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
 * Created by jan on 11. 7. 2016.
 */
public class MetroProductParser extends AbstractEshopProductParserNg {

    @Override
    protected Map<String, String> getCookies() {
        // 21 su Kosice pobocka
        return Collections.singletonMap("storeId", "21");
    }


    @Override
    protected boolean isProductUnavailable(Document document) {
        return ParserUtils.existElement(document, "div[class=action-ns]");
    }

    @Override
    protected BigDecimal parsePriceForPackage(Document document) {
        Elements elements = document.select("td[class=text-right]:has(strong)");
        String html = elements.get(0).html();
        int beginIndex = html.indexOf("|") + 2;
        int endIndex = html.indexOf("s DPH") - 2;
        html = html.substring(beginIndex, endIndex).trim();
        return new BigDecimal(html.replace(",", "."));
    }


    @Override
    protected String parseProductName(Document document) {
        return null;
    }

    @Override
    protected ProductAction parseAction(Document document) {
        return parseAction(document, "div[class=action-a]");
    }

    @Override
    protected Date parseActionValidity(Document document) {
        return null;
    }
}
