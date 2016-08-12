package sk.hudak.pricecomparator.server.eshops.bambino;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.canonical.ProductAction;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractEshopProductParserNg;
import sk.hudak.pricecomparator.server.async.ng.impl.ParserUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 11. 8. 2016.
 */
public class BambinoProductParser extends AbstractEshopProductParserNg {

    @Override
    protected boolean isProductUnavailable(Document document) {
        return ParserUtils.notExistElement(document, "button[class=button button--primary add-to-cart]");
    }

    @Override
    protected BigDecimal parsePriceForPackage(Document document) {
        //cena
        Elements elements = document.select("strong[itemprop=price]");
        StringBuffer sb = new StringBuffer(elements.get(0).text());
        sb = sb.deleteCharAt(0);
        sb = sb.deleteCharAt(0);
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
