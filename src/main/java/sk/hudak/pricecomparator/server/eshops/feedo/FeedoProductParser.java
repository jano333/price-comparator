package sk.hudak.pricecomparator.server.eshops.feedo;

import org.jsoup.nodes.Document;
import sk.hudak.pricecomparator.middle.model.ProductAction;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractEshopProductParserNg;
import sk.hudak.pricecomparator.server.async.ng.impl.ParserUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 11. 8. 2016.
 */
public class FeedoProductParser extends AbstractEshopProductParserNg {

    @Override
    protected int getTimeout() {
        //koli pomalym odozvam davam na 15 sekund
        return 15000;
    }

    @Override
    protected boolean isProductUnavailable(Document document) {
        return ParserUtils.existElement(document, "button[class=btn btn-danger btn-large cart]");
    }

    @Override
    protected BigDecimal parsePriceForPackage(Document document) {
        return null;
    }

    @Override
    protected String parseProductName(Document document) {
        return null;
    }

    @Override
    protected ProductAction parseAction(Document document) {
        boolean preminumCena = ParserUtils.existElement(document, "div[class=price price-premium]");
        boolean akcnaCena = ParserUtils.existElement(document, "div[class=price price-discount]");
        boolean action = preminumCena || akcnaCena;

        return action ? ProductAction.IN_ACTION : ProductAction.NON_ACTION;
    }

    @Override
    protected Date parseActionValidity(Document document) {
        return null;
    }
}
