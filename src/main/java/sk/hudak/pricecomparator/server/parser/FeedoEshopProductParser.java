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
    protected EshopProductInfo parsePrice(Document document) {
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
