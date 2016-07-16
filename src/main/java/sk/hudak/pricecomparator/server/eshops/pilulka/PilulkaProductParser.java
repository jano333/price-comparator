package sk.hudak.pricecomparator.server.eshops.pilulka;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.model.ProductAction;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractEshopProductParserNg;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 16. 7. 2016.
 */
public class PilulkaProductParser extends AbstractEshopProductParserNg {
    @Override
    protected boolean isProductUnavailable(Document document) {
        Elements elements = document.select("strong[id=priceNew]");
        return elements.isEmpty();
    }

    @Override
    protected BigDecimal parsePriceForPackage(Document document) {
        //<strong id="priceNew">12,99&nbsp;€</strong>
        //cena za balenie
        Elements elements = document.select("strong[id=priceNew]");
        StringBuffer sb = new StringBuffer(elements.get(0).text());
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
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
