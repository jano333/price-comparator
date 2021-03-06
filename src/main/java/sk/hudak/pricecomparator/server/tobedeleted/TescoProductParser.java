package sk.hudak.pricecomparator.server.tobedeleted;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.canonical.ProductAction;
import sk.hudak.pricecomparator.middle.utils.DateUtils;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractEshopProductParser;
import sk.hudak.pricecomparator.server.async.ng.impl.ParserUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 9. 7. 2016.
 */
@Deprecated
public class TescoProductParser extends AbstractEshopProductParser {

    @Override
    protected boolean isProductUnavailable(Document document) {
        return ParserUtils.notExistElement(document, "button[class=amount-adjust-button quantity-adjust]");
    }

    @Override
    protected BigDecimal parsePriceForPackage(Document document) {
        Elements elements = document.select("div[class=price-per-sellable-unit price-per-sellable-unit--price price-per-sellable-unit--price-per-item] div span span[class=value]");
        if (elements.isEmpty()) {
            return null;
        }
        String replace = elements.get(0).text().replace(",", ".");
        return new BigDecimal(replace);
    }

    @Override
    protected ProductAction parseAction(Document document) {
        return parseAction(document, "div[class=icon-offer-flash-group] div[class=red-square] span[class=text-position]");
    }

    @Override
    protected Date parseActionValidity(Document document) {
        StringBuilder htmlTree = new StringBuilder();
        htmlTree.append("ul[class=product-promotions]").append(" ");
        htmlTree.append("li[class=product-promotion]").append(" ");
        htmlTree.append("a").append(" ");
        htmlTree.append("div[class=list-item-content]").append(" ");
        htmlTree.append("span[class=dates]");

        Elements elements = document.select(htmlTree.toString());
        if (elements.isEmpty()) {
            return null;
        }

        StringBuilder sb = new StringBuilder(elements.get(0).text());
        sb = sb.delete(0, "Cena je platná pri dodaní do ".length());
        return DateUtils.parseDate(sb.toString(), DateUtils.DATE_FORMAT_HH_MM_YYYY);
    }


    @Override
    protected String parseProductName(Document document) {
        Elements elements = document.select("h1[class=product-title]");
        if (elements.isEmpty()) {
            return null;
        }
        return elements.get(0).text();
    }

    @Override
    protected String parsePictureUrl(Document document) {
        Elements elements = document.select("img[class=product-image]");
        if (elements.isEmpty()) {
            return null;
        }
        Element imgElement = elements.get(0);
        String src = imgElement.attr("src");
        return src;
    }
}
