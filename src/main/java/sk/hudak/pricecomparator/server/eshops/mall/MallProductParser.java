package sk.hudak.pricecomparator.server.eshops.mall;

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
 * Created by jan on 19. 7. 2016.
 */
public class MallProductParser extends AbstractEshopProductParser {

    @Override
    protected boolean isProductUnavailable(Document document) {
        return ParserUtils.notExistElement(document, "button[id=add-to-cart]");
    }

    @Override
    protected BigDecimal parsePriceForPackage(Document document) {
        Elements elements2 = document.select("b[class=pro-price con-emphasize font-primary--bold mr-5]");
        if (elements2.isEmpty()) {
            //TODO vynimka by mala byt...
            return null;
        }

        String cenaZaBalenie = elements2.get(0).text().replace("€", "").replace(",", ".").trim();
        return new BigDecimal(cenaZaBalenie);

    }

    @Override
    protected ProductAction parseAction(Document document) {
        return ParserUtils.existElement(document, "em[class=label label--action")
                ? ProductAction.IN_ACTION
                : ProductAction.NON_ACTION;
    }

    @Override
    protected Date parseActionValidity(Document document) {
        Elements select = document.select("p[class=mb-5]");
        if (select.isEmpty()) {
            return null;
        }
        Element element = select.get(0);
        StringBuilder sb = new StringBuilder(element.html());
        sb.delete(0, sb.indexOf("do") + 3);
        sb.delete(10, sb.length());
        return DateUtils.parseDate(sb.toString(), DateUtils.DATE_FORMAT_HH_MM_YYYY);
    }

    @Override
    protected String parseProductName(Document document) {
        //TODO
        return null;
    }
}
