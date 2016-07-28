package sk.hudak.pricecomparator.server.tobedeleted;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.model.EshopProductInfo;
import sk.hudak.pricecomparator.server.core.AbstractEshopProductInfo;
import sk.hudak.pricecomparator.server.core.AbstractEshopProductParser;

import java.math.BigDecimal;

/**
 * Created by jan on 4. 12. 2015.
 */
@Deprecated
public class AndreaEshopProductParser extends AbstractEshopProductParser {

    @Override
    protected EshopProductInfo parsePrice(Document document) {

        Elements elements = document.select("div[class=value]");
        StringBuffer sb = new StringBuffer(elements.get(0).text());
        sb = sb.deleteCharAt(sb.length() - 1);
        sb = sb.deleteCharAt(sb.length() - 1);
        final String cenaZaBalenie = sb.toString().replace(",", ".");

        return new AbstractEshopProductInfo(parserInputData) {

            @Override
            public BigDecimal getPriceForPackage() {
                return new BigDecimal(cenaZaBalenie);
            }
        };
    }
}
