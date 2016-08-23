package sk.hudak.pricecomparator.server.tobedeleted;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.math.BigDecimal;

/**
 * Created by jan on 23. 5. 2016.
 */
@Deprecated
public class LekarenBellaProductParser extends AbstractEshopProductParser {
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
        return notExistElement(document, "input[class=koupitbutton]");    }

    private String parseProductName(Document document) {
        //TODO impl
        return null;
    }

    private String parseCenaZaBalenie(Document document) {
        Elements elements = document.select("div[class=cena] span");
        if (elements.isEmpty()) {
            return null;
        }
        String text = elements.get(0).text();
        text = StringUtils.removeStart(text, "Cena: ");
        return removeLastCharacters(replaceAllCommaForDot(text), 2);
    }

}

