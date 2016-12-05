package sk.hudak.pricecomparator.server.tobedeleted;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.canonical.ProductAction;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractEshopProductParser;
import sk.hudak.pricecomparator.server.async.ng.impl.ParserUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 11. 8. 2016.
 */
@Deprecated
public class FeedoProductParser extends AbstractEshopProductParser {

    @Override
    protected int getTimeout() {
        //koli pomalym odozvam davam na 15 sekund
        return 15000;
    }

    @Override
    protected boolean isProductUnavailable(Document document) {
        return ParserUtils.notExistElement(document, "button[class=btn btn-danger btn-large cart]");
    }

    @Override
    protected BigDecimal parsePriceForPackage(Document document) {
        // skusim -> premim cena
        Elements select = document.select("div[class=price price-premium] span");
        if (!select.isEmpty()) {
        } else {
            // ak sa nenajde tak skusim -> akcna cena
            select = document.select("div[class=price price-discount] span");
            if (!select.isEmpty()) {
            } else {
                // ak sa nenajde tak skusim -> normalna cena
                select = document.select("div[class=price price-base] span");
                if (select.isEmpty()) {
                    logger.error("cena pre dany produkt sa nenasla");
                    //TODO vynimka
                    return null;
                }
            }
        }

        Element element1 = select.get(0);
        String html = element1.html();
        if (StringUtils.isBlank(html)) {
            //TODO vynimka
            return null;
        }
        String cenaZaBalenie = html.substring(0, html.indexOf("&nbsp;")).replace(",", ".");
        return new BigDecimal(cenaZaBalenie);
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
        return ACTION_VALIDITY_NOT_DEFINE;
    }

    @Override
    protected String parseProductName(Document document) {
        //TODO parseProductName
        return null;
    }
}
