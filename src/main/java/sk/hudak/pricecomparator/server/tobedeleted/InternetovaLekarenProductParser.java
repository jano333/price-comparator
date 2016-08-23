package sk.hudak.pricecomparator.server.tobedeleted;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.math.BigDecimal;

/**
 * Created by jan on 7. 12. 2015.
 */
@Deprecated
public class InternetovaLekarenProductParser extends AbstractEshopProductParser {

    @Override
    protected EshopProductInfo parsePrice(Document document) {

        //cena
        Elements elements = document.select("strong[itemprop=price]");
        StringBuffer sb = new StringBuffer(elements.get(0).text());
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
