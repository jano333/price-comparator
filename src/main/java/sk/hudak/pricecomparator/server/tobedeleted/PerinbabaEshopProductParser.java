package sk.hudak.pricecomparator.server.tobedeleted;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.math.BigDecimal;

/**
 * Created by jan on 4. 12. 2015.
 */
@Deprecated
public class PerinbabaEshopProductParser extends AbstractEshopProductParser {
    @Override
    protected EshopProductInfo parsePrice(Document document) {
        //cena
        Elements elements = document.select("span[class=price]");
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
