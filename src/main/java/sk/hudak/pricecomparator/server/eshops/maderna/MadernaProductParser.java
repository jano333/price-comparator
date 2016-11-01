package sk.hudak.pricecomparator.server.eshops.maderna;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.canonical.ProductAction;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractEshopProductParser;
import sk.hudak.pricecomparator.server.async.ng.impl.ParserUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 10. 9. 2016.
 */
public class MadernaProductParser extends AbstractEshopProductParser {
    @Override
    protected boolean isProductUnavailable(Document document) {
        Elements elements = document.select("form#addtocart > div > input[class]");
        if(elements.size()<2){
            return true;
        }
        //TODO zistit preco nefunguje...
//        boolean nostExist = ParserUtils.notExistElement(document, "form#addtocart > div > input[class=\" button prod_add_to_cart\"]");
        return false;
    }

    @Override
    protected BigDecimal parsePriceForPackage(Document document) {
        Elements elements = document.select("span[id=product-detail-price-value]");
        if (elements.isEmpty()) {
            //TODO tu by mala byt vynimka ked bduem mat ze je nedostupne
            return null;
        }
        String text = elements.get(0).text();
        if (StringUtils.isBlank(text)) {
            return null;
        }
        String cena = ParserUtils.removeLastCharacters(text, 2);
        return new BigDecimal(ParserUtils.replaceAllCommaForDot(cena));
    }

    @Override
    protected ProductAction parseAction(Document document) {
        return null;
    }

    @Override
    protected Date parseActionValidity(Document document) {
        return null;
    }

    @Override
    protected String parseProductName(Document document) {
        return null;
    }
}
