package sk.hudak.pricecomparator.server.tobedeleted;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.math.BigDecimal;

/**
 * Created by jan on 7. 11. 2015.
 */
@Deprecated
public class SemistorEshopProductParser extends AbstractEshopProductParser {
    @Override
    protected EshopProductInfo parsePrice(Document document) {

        //cena
        Elements elements = document.select("div[class=current]");

        Element selected = null;
        for (Element element : elements) {
            if (element.hasAttr("id")) {
                selected = element;
                break;
            }
        }
        final String cenaZaBalenie = selected.text().replace("EUR.", "").replace(",", ".").trim();

        return new AbstractEshopProductInfo(parserInputData) {

            @Override
            public BigDecimal getPriceForPackage() {
                return new BigDecimal(cenaZaBalenie);
            }
        };


    }

}
