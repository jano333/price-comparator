package sk.hudak.pricecomparator.server.eshops.andrea;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.canonical.ProductAction;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractEshopProductParserNg;
import sk.hudak.pricecomparator.server.async.ng.impl.ParserUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 28. 7. 2016.
 */
public class AndreaProductParser extends AbstractEshopProductParserNg {
    @Override
    protected boolean isProductUnavailable(Document document) {
        return ParserUtils.notExistElement(document, "a[class=btnx normal green buy]");
    }

    @Override
    protected BigDecimal parsePriceForPackage(Document document) {
        Elements elements = document.select("div[class=value]");
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
