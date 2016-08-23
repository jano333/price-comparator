package sk.hudak.pricecomparator.server.tobedeleted;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.canonical.ProductAction;

import java.math.BigDecimal;

/**
 * Created by jan on 13. 10. 2015.
 */
@Deprecated
public class FeedoEshopProductParser extends AbstractEshopProductParser {

    @Override
    protected int getTimeout() {
        //koli pomalym odozvam davam na 15 sekund
        return 15000;
    }

    @Override
    protected EshopProductInfo parsePrice(Document document) {
        // overim ci je tam button na pridanie do kosika,
        // lebo bol produkt kde bola cena ale uz bol nedostupny a zlozity text tam je,
        // tak idem radsej otestovat ci je tam button pre pridanie do kosika,
        // ak nie je, tak koncim
        Elements elements = document.select("button[class=btn btn-danger btn-large cart]");
        if (elements.isEmpty()) {
            logger.error("produkt nedostupny");
            return ProductInfoFactory.createUnaviable();
        }

        boolean inAction = false;

        // skusim -> premim cena
        Elements select = document.select("div[class=price price-premium]");
        if (!select.isEmpty()) {
            inAction = true;
        } else {
            // ak sa nenajde tak skusim -> akcna cena
            select = document.select("div[class=price price-discount]");
            if (!select.isEmpty()) {
                inAction = true;
            } else {
                // ak sa nenajde tak skusim -> normalna cena
                select = document.select("div[class=price price-base]");
                if (select.isEmpty()) {
                    logger.error("cena pre dany produkt sa nenasla");
                    return ProductInfoFactory.createUnaviable();
                }
            }
        }

        Element element1 = select.get(0);
        Elements children = element1.children();
        if (children.isEmpty()) {
            return ProductInfoFactory.createUnaviable();
        }
        Element element = element1.child(0);
        String html = element.html();
        if (StringUtils.isBlank(html)) {
            return ProductInfoFactory.createUnaviable();
        }

        final String cenaZaBalenie;
        try {
            cenaZaBalenie = html.substring(0, html.indexOf("&nbsp;")).replace(",", ".");
        } catch (Exception e) {
            //TODO error
            e.printStackTrace();
            return ProductInfoFactory.createUnaviable();
        }

        final boolean finalInAction = inAction;
        return new AbstractEshopProductInfo(parserInputData) {
            @Override
            public BigDecimal getPriceForPackage() {
                return new BigDecimal(cenaZaBalenie);
            }

            @Override
            public ProductAction getAction() {
                if (finalInAction) {
                    return ProductAction.IN_ACTION;
                } else {
                    return ProductAction.NON_ACTION;
                }
            }
        };

    }


}
