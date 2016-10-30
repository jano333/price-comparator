package sk.hudak.pricecomparator.server.eshops.hej;

import org.jsoup.nodes.Document;
import sk.hudak.pricecomparator.middle.canonical.ProductAction;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractEshopProductParserNg;
import sk.hudak.pricecomparator.server.async.ng.impl.ParserUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 23. 8. 2016.
 */
public class HejProductParser extends AbstractEshopProductParserNg {

    @Override
    protected boolean isProductUnavailable(Document document) {
        return ParserUtils.notExistElement(document, "p[id=vlozit_do_kosiku]");
    }

    @Override
    protected BigDecimal parsePriceForPackage(Document document) {
        final String cenaZaBalenie = document.select("span#real_price").html();
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
