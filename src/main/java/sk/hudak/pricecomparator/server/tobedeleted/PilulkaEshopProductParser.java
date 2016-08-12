package sk.hudak.pricecomparator.server.tobedeleted;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.exeption.PriceComparatorException;

import java.math.BigDecimal;

/**
 * Created by jan on 29. 11. 2015.
 */
@Deprecated
public class PilulkaEshopProductParser extends AbstractEshopProductParser {


    @Override
    protected EshopProductInfo parsePrice(Document document) {
        //<strong id="priceNew">12,99&nbsp;€</strong>
        //cena za balenie
        Elements elements = document.select("strong[id=priceNew]");
        if (elements.isEmpty()) {
            throw new PriceComparatorException("element not found");
        }
        StringBuffer sb = new StringBuffer(elements.get(0).text());
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        final String cenaZaBalenie = sb.toString().replace(",", ".");

        return new AbstractEshopProductInfo(parserInputData) {

            @Override
            public BigDecimal getPriceForPackage() {
                return new BigDecimal(cenaZaBalenie);
            }

        };
    }
}
