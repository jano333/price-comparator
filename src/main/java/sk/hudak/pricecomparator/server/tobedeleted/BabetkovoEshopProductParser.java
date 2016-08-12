package sk.hudak.pricecomparator.server.tobedeleted;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.math.BigDecimal;

/**
 * Created by jan on 8. 5. 2016.
 */
@Deprecated
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
        text = replaceAllCommaForDot(text);
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
