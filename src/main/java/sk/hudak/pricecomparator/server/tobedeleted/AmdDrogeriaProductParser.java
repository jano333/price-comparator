package sk.hudak.pricecomparator.server.tobedeleted;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.model.EshopProductInfo;
import sk.hudak.pricecomparator.server.core.AbstractEshopProductInfo;
import sk.hudak.pricecomparator.server.core.AbstractEshopProductParser;
import sk.hudak.pricecomparator.server.factory.ProductInfoFactory;

import java.math.BigDecimal;

/**
 * Created by jan on 7. 5. 2016.
 */
@Deprecated
public class AmdDrogeriaProductParser extends AbstractEshopProductParser {

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
        return notExistElement(document, "input[class=addtocart-button]");
    }

    private String parseProductName(Document document) {
        Elements elements = document.select("div[class=productdetailsin] h1");
        if (elements.isEmpty()) {
            return null;
        }
        String text = elements.get(0).text();
        return text;
    }

    private String parseCenaZaBalenie(Document document) {
        Elements elements = document.select("span[class=PricesalesPrice]");
        if (elements.isEmpty()) {
            return null;
        }
        String str = removeLastCharacters(elements.get(0).text(), 2);
        String replace = str.replace(",", ".");
        return replace;
    }

}
