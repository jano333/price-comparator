package sk.hudak.pricecomparator.server.eshops.babetkovo;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.model.EshopProductInfo;
import sk.hudak.pricecomparator.server.core.AbstractEshopProductInfo;
import sk.hudak.pricecomparator.server.core.AbstractEshopProductParser;
import sk.hudak.pricecomparator.server.factory.ProductInfoFactory;

import java.math.BigDecimal;

/**
 * Created by jan on 8. 5. 2016.
 */
public class BabetkovoEshopProductParser extends AbstractEshopProductParser {

    @Override
    protected EshopProductInfo parsePrice(Document document) {
        if (isProductNedostupny(document)) {
            logger.error("produkt nie je dostupny: " + parserInputData.getEshopProductPage());
            return ProductInfoFactory.createUnaviable();
        }

        final String productName = parseProductName(document);

        final String cenaZaBalenie = parseCenaZaBalenie(document);

        return new AbstractEshopProductInfo(parserInputData) {

            @Override
            public BigDecimal getPriceForPackage() {
                return new BigDecimal(cenaZaBalenie);
            }

            @Override
            public String getProductNameInEhop() {
                return productName;
            }
        };
    }

    private String parseCenaZaBalenie(Document document) {
        Elements elements = document.select("div[class=price]");
        if (elements.isEmpty()) {
            return null;
        }
        String text = elements.get(0).text();
        text = text.substring(0, text.indexOf("€"));
        text = text.replace(",", ".");
        return text;
    }

    private String parseProductName(Document document) {
        //TODO
        return null;
    }

    private boolean isProductNedostupny(Document document) {
        return notExistElement(document, "input[id=button-cart]");
    }
}
