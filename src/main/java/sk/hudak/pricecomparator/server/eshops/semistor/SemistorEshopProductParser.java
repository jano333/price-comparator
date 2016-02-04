package sk.hudak.pricecomparator.server.eshops.semistor;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.model.EshopProductInfo;
import sk.hudak.pricecomparator.server.core.AbstractEshopProductInfo;
import sk.hudak.pricecomparator.server.core.AbstractEshopProductParser;

import java.math.BigDecimal;

/**
 * Created by jan on 7. 11. 2015.
 */
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
