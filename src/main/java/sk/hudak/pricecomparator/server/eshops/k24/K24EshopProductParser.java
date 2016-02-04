package sk.hudak.pricecomparator.server.eshops.k24;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.model.EshopProductInfo;
import sk.hudak.pricecomparator.server.core.AbstractEshopProductInfo;
import sk.hudak.pricecomparator.server.core.AbstractEshopProductParser;

import java.math.BigDecimal;

/**
 * Created by jan on 29. 11. 2015.
 */
public class K24EshopProductParser extends AbstractEshopProductParser {

    @Override
    protected EshopProductInfo parsePrice(Document document) {
        //<div class="altFontFace price">   3,96 € </div>
        //cena
        Elements elements = document.select("div[class=altFontFace price]");
        StringBuffer sb = new StringBuffer(elements.get(0).text().trim());
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
