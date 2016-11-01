package sk.hudak.pricecomparator.server.eshops.pilulka;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.canonical.ProductAction;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractEshopProductParser;
import sk.hudak.pricecomparator.server.async.ng.impl.ParserUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 16. 7. 2016.
 */
public class PilulkaProductParser extends AbstractEshopProductParser {
    @Override
    protected boolean isProductUnavailable(Document document) {
        return ParserUtils.notExistElement(document, "span[class=inline-btn btn-purple btn-h44 btn-basket before]");
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
