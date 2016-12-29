package sk.hudak.pricecomparator.server.eshops.ng.feedo;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.hudak.pricecomparator.middle.canonical.ProductAction;
import sk.hudak.pricecomparator.server.html.jsoup.JsoupParserUtils;
import sk.hudak.pricecomparator.server.html.jsoup.JsoupProductParser;

import javax.inject.Named;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 4. 12. 2016.
 */
@Named
public class FeedoProductParser extends JsoupProductParser {

    private static Logger logger = LoggerFactory.getLogger(FeedoProductParser.class);

    @Override
    protected int getTimeout() {
        //koli pomalym odozvam davam na 15 sekund
        return 15000;
    }

    @Override
    protected boolean isProductUnavailable(Document document) {
        return JsoupParserUtils.notExistElement(document, "button[class=btn btn-danger btn-large cart]");
    }

    @Override
    protected BigDecimal parseProductPriceForPackage(Document document) {
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
    protected ProductAction parseProductAction(Document document) {
        boolean preminumCena = JsoupParserUtils.existElement(document, "div[class=price price-premium]");
        boolean akcnaCena = JsoupParserUtils.existElement(document, "div[class=price price-discount]");
        boolean action = preminumCena || akcnaCena;

        return action ? ProductAction.IN_ACTION : ProductAction.NON_ACTION;
    }

    @Override
    protected Date parseProductActionValidity(Document document) {
        return ACTION_VALIDITY_NOT_DEFINE;
    }

    @Override
    protected String parseProductName(Document document) {
        return document.select("h1[class=product-detail-heading hidden-xs hidden-sm]").get(0).text();
    }

    @Override
    protected String parseProductPictureURL(Document document) {
        return document.select("a[class=image]").get(0).attr("href");
    }
}
