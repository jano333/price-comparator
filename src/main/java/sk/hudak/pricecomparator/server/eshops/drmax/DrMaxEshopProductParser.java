package sk.hudak.pricecomparator.server.eshops.drmax;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.model.EshopProductInfo;
import sk.hudak.pricecomparator.server.core.AbstractEshopProductInfo;
import sk.hudak.pricecomparator.server.core.AbstractEshopProductParser;

import java.math.BigDecimal;

/**
 * Created by jan on 3. 1. 2016.
 */
public class DrMaxEshopProductParser extends AbstractEshopProductParser {

    @Override
    protected EshopProductInfo parsePrice(Document document) {

        Elements elements = document.select("strong[itemprop=price]");
        StringBuffer sb = new StringBuffer(elements.get(0).text());
        final String cenaZaBalenie = sb.toString().replace(",", ".");

        return new AbstractEshopProductInfo(parserInputData) {

            @Override
            public BigDecimal getPriceForPackage() {
                return new BigDecimal(cenaZaBalenie);
            }
        };

    }
}
