package sk.hudak.pricecomparator.server.eshops.ng.tesco;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.canonical.ProductAction;
import sk.hudak.pricecomparator.middle.utils.DateUtils;
import sk.hudak.pricecomparator.server.html.parser.JsoupParserUtils;
import sk.hudak.pricecomparator.server.html.parser.JsoupProductParser;

import javax.inject.Named;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 3. 11. 2016.
 */
@Named
public class TescoProductParser extends JsoupProductParser {

    @Override
    protected boolean isProductUnavailable(Document document) {
        return JsoupParserUtils.notExistElement(document, "button[class=amount-adjust-button quantity-adjust]");
    }

    @Override
    protected BigDecimal parseProductPriceForPackage(Document document) {
        Elements elements = document.select("div[class=price-per-sellable-unit price-per-sellable-unit--price price-per-sellable-unit--price-per-item] div span span[class=value]");
        return new BigDecimal(elements.get(0).text().replace(",", "."));
    }

    @Override
    protected ProductAction parseProductAction(Document document) {
        return JsoupParserUtils.parseAction(document, "div[class=icon-offer-flash-group] div[class=red-square] span[class=text-position]");
    }

    @Override
    protected Date parseProductActionValidity(Document document) {
        StringBuilder htmlTree = new StringBuilder()
                .append("ul[class=product-promotions]").append(" ")
                .append("li[class=product-promotion]").append(" ")
                .append("a").append(" ")
                .append("div[class=list-item-content]").append(" ")
                .append("span[class=dates]");

        StringBuilder sb = new StringBuilder(document.select(htmlTree.toString()).get(0).text());
        sb = sb.delete(0, "Cena je platná pri dodaní do ".length());
        return DateUtils.parseDate(sb.toString(), DateUtils.DATE_FORMAT_HH_MM_YYYY);
    }

    @Override
    protected String parseProductName(Document document) {
        return document.select("h1[class=product-title]").get(0).text();
    }

    @Override
    protected String parseProductPictureURL(Document document) {
        return document.select("img[class=product-image]").get(0).attr("src");
    }
}
