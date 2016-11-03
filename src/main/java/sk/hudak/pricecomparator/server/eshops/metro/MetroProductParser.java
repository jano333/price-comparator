package sk.hudak.pricecomparator.server.eshops.metro;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.canonical.ProductAction;
import sk.hudak.pricecomparator.middle.utils.DateUtils;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractEshopProductParser;
import sk.hudak.pricecomparator.server.async.ng.impl.ParserUtils;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

/**
 * Created by jan on 11. 7. 2016.
 */
public class MetroProductParser extends AbstractEshopProductParser {

    @Override
    protected Map<String, String> getCookies() {
        // 21 su Kosice pobocka
        return Collections.singletonMap("storeId", "21");
    }

    @Override
    protected boolean isProductUnavailable(Document document) {
        return ParserUtils.existElement(document, "div[class=product-availability m-b-sm] span[class=icon-neskladem]");
    }

    @Override
    protected BigDecimal parsePriceForPackage(Document document) {
        Elements elements = document.select("tr[class=price-package] td:nth-child(4)");
        String html = elements.get(0).html();
        html = ParserUtils.removeLastCharacters(html, 2);
        html = ParserUtils.replaceAllCommaForDot(html);
        return new BigDecimal(html);
    }

    @Override
    protected ProductAction parseAction(Document document) {
        //TODO overit urobili redesign
        return parseAction(document, "div[class=action-a]");
    }

    @Override
    protected Date parseActionValidity(Document document) {
        //TODO overit urobili redesign
        Elements elements = document.select("div[class=action-l clearfix]");
        if (elements.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder(elements.get(0).html());
        sb = sb.delete(0, "Platnosť akcie do ".length());
        return DateUtils.parseDate(sb.toString(), DateUtils.DATE_FORMAT_HH_MM_YYYY);
    }

    @Override
    protected String parseProductName(Document document) {
        //TODO parseProductName
        return null;
    }
}
