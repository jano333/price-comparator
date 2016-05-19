package sk.hudak.pricecomparator.server.eshops.bugy;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.model.EshopProductInfo;
import sk.hudak.pricecomparator.server.core.AbstractEshopProductInfo;
import sk.hudak.pricecomparator.server.core.AbstractEshopProductParser;
import sk.hudak.pricecomparator.server.factory.ProductInfoFactory;

import java.math.BigDecimal;

/**
 * Created by jan on 19. 5. 2016.
 */
public class BugyEshopProductParser extends AbstractEshopProductParser {

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

    private boolean isProductNedostupny(Document document) {
        return notExistElement(document, "button[class=add_to_card]");    }

    private String parseProductName(Document document) {
        //div[id="product_text"] h1
        //TODO impl
        return null;
    }

    private String parseCenaZaBalenie(Document document) {
        Elements elements = document.select("div[id=product_price] span");
        if (elements.isEmpty()) {
            return null;
        }
        String text = elements.get(0).text();
        text = text.replace(",", ".");
        return text;
    }
}
