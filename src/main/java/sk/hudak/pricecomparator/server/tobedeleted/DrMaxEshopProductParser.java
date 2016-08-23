package sk.hudak.pricecomparator.server.tobedeleted;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.math.BigDecimal;

/**
 * Created by jan on 3. 1. 2016.
 */
@Deprecated
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
