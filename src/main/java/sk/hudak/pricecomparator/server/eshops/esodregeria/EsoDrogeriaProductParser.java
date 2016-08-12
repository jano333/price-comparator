package sk.hudak.pricecomparator.server.eshops.esodregeria;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.server.tobedeleted.AbstractEshopProductInfo;
import sk.hudak.pricecomparator.server.tobedeleted.AbstractEshopProductParser;
import sk.hudak.pricecomparator.server.tobedeleted.EshopProductInfo;
import sk.hudak.pricecomparator.server.tobedeleted.ProductInfoFactory;

import java.math.BigDecimal;

/**
 * Created by jan on 29. 5. 2016.
 */
public class EsoDrogeriaProductParser extends AbstractEshopProductParser {

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
        return notExistElement(document, "input[id=buy_btn]");    }

    private String parseProductName(Document document) {
        //div[id="product_text"] h1
        //TODO impl
        return null;
    }

    private String parseCenaZaBalenie(Document document) {
        Elements elements = document.select("span[class=price-value def_color]");
        if (elements.isEmpty()) {
            return null;
        }
        String text = elements.get(0).text();
        //15,99&nbsp;EUR
        return removeLastCharacters(replaceAllCommaForDot(text), 4);
    }

}
