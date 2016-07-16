package sk.hudak.pricecomparator.server.eshops.semistor;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.model.ProductAction;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractEshopProductParserNg;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 16. 7. 2016.
 */
public class SemistorProductParser extends AbstractEshopProductParserNg {
    @Override
    protected boolean isProductUnavailable(Document document) {
        //TODO
        return false;
    }

    @Override
    protected BigDecimal parsePriceForPackage(Document document) {
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
        return new BigDecimal(cenaZaBalenie);
    }

    @Override
    protected String parseProductName(Document document) {
        return null;
    }

    @Override
    protected ProductAction parseAction(Document document) {
        return null;
    }

    @Override
    protected Date parseActionValidity(Document document) {
        return null;
    }
}
