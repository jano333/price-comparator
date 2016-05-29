package sk.hudak.pricecomparator.server.eshops.bambino;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.model.EshopProductInfo;
import sk.hudak.pricecomparator.server.core.AbstractEshopProductInfo;
import sk.hudak.pricecomparator.server.core.AbstractEshopProductParser;

import java.math.BigDecimal;

/**
 * Created by jan on 9. 12. 2015.
 */
public class BambinoEshopProductParser extends AbstractEshopProductParser {


    @Override
    protected EshopProductInfo parsePrice(Document document) {
//        if (isProductNedostupny(document)) {
//            logger.error("produkt nie je dostupny: " + parserInputData.getEshopProductPage());
//            return ProductInfoFactory.createUnaviable();
//        }

        //cena
        Elements elements = document.select("strong[itemprop=price]");
        StringBuffer sb = new StringBuffer(elements.get(0).text());
        sb = sb.deleteCharAt(0);
        sb = sb.deleteCharAt(0);
        final String cenaZaBalenie = sb.toString().replace(",", ".");

        return new AbstractEshopProductInfo(parserInputData) {

            @Override
            public BigDecimal getPriceForPackage() {
                return new BigDecimal(cenaZaBalenie);
            }
        };
    }

    private boolean isProductNedostupny(Document document) {
        return notExistElement(document, "button[class=add_to_cart]");
    }
}
