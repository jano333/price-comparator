package sk.hudak.pricecomparator.server.eshops.perinbaba;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.model.ProductAction;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractEshopProductParserNg;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 18. 7. 2016.
 */
public class PerinbabaProductParser extends AbstractEshopProductParserNg {
    @Override
    protected boolean isProductUnavailable(Document document) {
        //TODO
        return false;
    }

    @Override
    protected BigDecimal parsePriceForPackage(Document document) {
        Elements elements = document.select("span[class=price]");
        StringBuffer sb = new StringBuffer(elements.get(0).text());
        sb = sb.deleteCharAt(sb.length() - 1);
        sb = sb.deleteCharAt(sb.length() - 1);
        final String cenaZaBalenie = sb.toString().replace(",", ".");

        return new BigDecimal(cenaZaBalenie);
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
