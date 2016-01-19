package sk.hudak.pricecomparator.server.parser;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.api.model.EshopProductInfo;
import sk.hudak.pricecomparator.server.core.AbstractEshopProductInfo;
import sk.hudak.pricecomparator.server.core.AbstractEshopProductParser;
import sk.hudak.pricecomparator.server.factory.ProductInfoFactory;

import java.math.BigDecimal;

/**
 * Created by jan on 13. 10. 2015.
 */
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
        Elements elements = document.select("button[class=btn btn-danger btn-large]");
        if (elements.isEmpty()) {
            System.err.println("produkt nedostupny");
            return ProductInfoFactory.createUnaviable();
        }


        // skusim -> premim cena
        Elements select = document.select("div[class=price-premium]");
        // ak sa nenajde tak skusim -> akcna cena
        if (select.isEmpty()) {
            select = document.select("div[class=price-discount]");
        }
        // ak sa nenajde tak skusim -> normalna cena
        if (select.isEmpty()) {
            select = document.select("div[class=price]");
        }
        if (select.isEmpty()) {
            return ProductInfoFactory.createUnaviable();
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
            e.printStackTrace();
            return ProductInfoFactory.createUnaviable();
        }

        return new AbstractEshopProductInfo(parserInputData) {
            @Override
            public BigDecimal getPriceForPackage() {
                return new BigDecimal(cenaZaBalenie);
            }
        };

    }


}
