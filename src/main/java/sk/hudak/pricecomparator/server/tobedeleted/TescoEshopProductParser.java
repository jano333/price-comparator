package sk.hudak.pricecomparator.server.tobedeleted;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.model.EshopProductInfo;
import sk.hudak.pricecomparator.middle.model.ProductAction;
import sk.hudak.pricecomparator.server.core.AbstractEshopProductInfo;
import sk.hudak.pricecomparator.server.core.AbstractEshopProductParser;
import sk.hudak.pricecomparator.server.factory.ProductInfoFactory;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jan on 13. 10. 2015.
 */
@Deprecated
public class TescoEshopProductParser extends AbstractEshopProductParser {

    @Override
    protected EshopProductInfo parsePrice(Document document) {
        if (isProductNedostupny(document)) {
            logger.error("produkt nie je dostupny: " + parserInputData.getEshopProductPage());
            return ProductInfoFactory.createUnaviable();
        }

        final String productName = parseProductName(document);

        final String cenaZaBalenie = parseCenaZaBalenie(document);

        final boolean isInAction = parseAction(document);

        Date actionTo = null;
        if (isInAction) {
            actionTo = parseActionValidity(document);
        }
        final Date finalActionTo = actionTo;


        return new AbstractEshopProductInfo(parserInputData) {

            @Override
            public ProductAction getAction() {
                return isInAction ? ProductAction.IN_ACTION : ProductAction.NON_ACTION;
            }

            @Override
            public Date getActionValidTo() {
                return finalActionTo;
            }

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
        Elements elements = document.select("button[class=amount-adjust-button quantity-adjust]");
        return elements.isEmpty();
    }

    /**
     * @param document
     * @return null, ak sa nenajde
     */
    private String parseProductName(Document document) {
        Elements elements = document.select("h1[class=product-title]");
        if (elements.isEmpty()) {
            return null;
        }
        return elements.get(0).text();
    }

    /**
     * @param document
     * @return null ak sa nenajde
     */
    private String parseCenaZaBalenie(Document document) {
        Elements elements = document.select("div[class=price-per-sellable-unit price-per-sellable-unit--price price-per-sellable-unit--price-per-item] div span span[class=value]");
        if (elements.isEmpty()) {
            return null;
        }
        return elements.get(0).text().replace(",", ".");
    }

    private boolean parseAction(Document document) {
        Elements elements = document.select("div[class=icon-offer-flash-group] div[class=red-square] span[class=text-position]");
        return !elements.isEmpty();
    }

    private Date parseActionValidity(Document document) {
        StringBuilder htmlTree = new StringBuilder();
        htmlTree.append("ul[class=product-promotions]").append(" ");
        htmlTree.append("li[class=product-promotion]").append(" ");
        htmlTree.append("a").append(" ");
        htmlTree.append("div[class=list-item-content]").append(" ");
        htmlTree.append("span[class=dates]");

        Elements elements = document.select(htmlTree.toString());
        if (elements.isEmpty()) {
            return null;
        }

        StringBuilder sb = new StringBuilder(elements.get(0).text());
        sb = sb.delete(0, "Cena je platná pri dodaní do ".length());
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            return sdf.parse(sb.toString());
        } catch (ParseException e) {
            //TODO vynimka
            e.printStackTrace();
            return null;
        }
    }


}
