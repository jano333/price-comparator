package sk.hudak.pricecomparator.server.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.api.exeption.PriceComparatorException;
import sk.hudak.pricecomparator.middle.api.model.EshopProductInfo;
import sk.hudak.pricecomparator.server.core.AbstractEshopProductInfo;
import sk.hudak.pricecomparator.server.core.AbstractEshopProductParser;

import java.math.BigDecimal;

/**
 * Created by jan on 13. 10. 2015.
 */
public class FeedoEshopProductParser extends AbstractEshopProductParser {

    @Override
    protected EshopProductInfo parsePrice(Document document) {
        // skusim premim cenu
        Elements select = document.select("div[class=price-premium]");
        // ak sa nenajde tak skusim akcnu cenu
        if (select.isEmpty()) {
            select = document.select("div[class=price-discount]");
        } else {
            throw new PriceComparatorException("Price element value not found.");
        }
        Element element = select.get(0).child(0);
        String html = element.html();
        final String cenaZaBalenie = html.substring(0, html.indexOf("&nbsp;")).replace(",", ".");

        return new AbstractEshopProductInfo(parserInputData) {
            @Override
            public BigDecimal getPriceForPackage() {
                return new BigDecimal(cenaZaBalenie);
            }
        };

    }


}
