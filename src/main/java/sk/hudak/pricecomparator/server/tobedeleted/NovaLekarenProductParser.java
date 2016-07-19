package sk.hudak.pricecomparator.server.tobedeleted;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.model.EshopProductInfo;
import sk.hudak.pricecomparator.server.core.AbstractEshopProductInfo;
import sk.hudak.pricecomparator.server.core.AbstractEshopProductParser;
import sk.hudak.pricecomparator.server.factory.ProductInfoFactory;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;

/**
 * Created by jan on 2. 6. 2016.
 */
@Deprecated
public class NovaLekarenProductParser extends AbstractEshopProductParser {

    @Override
    protected Map<String, String> getCookies() {
        return Collections.singletonMap("jazykx", "sk");
    }

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
        return notExistElement(document, "input[class=subimg]");
    }

    private String parseProductName(Document document) {
        //div[id="product_text"] h1
        //TODO impl
        return null;
    }

    private String parseCenaZaBalenie(Document document) {
        Elements elements = document.select("font[color=#00aeff] b");
        if (elements.isEmpty()) {
            return null;
        }
        String text = elements.get(0).text();
        return removeLastCharacters(replaceAllCommaForDot(text), 2);
    }
}
