package sk.hudak.pricecomparator.server.eshops.alza;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.canonical.ProductAction;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractEshopProductParserNg;
import sk.hudak.pricecomparator.server.async.ng.impl.ParserUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 11. 7. 2016.
 */
public class AlzaProductParser extends AbstractEshopProductParserNg {

    @Override
    protected boolean isProductUnavailable(Document document) {
        return ParserUtils.notExistElement(document, "a[class=cart-insert]")
                && ParserUtils.notExistElement(document, "a[class=btnx normal green buy]");
    }

    @Override
    protected BigDecimal parsePriceForPackage(Document document) {
        //niekte je to tak
        Elements elements = document.select("span[class=price_withVat]");
        if (elements.isEmpty()) {
            // inde tak
            elements = document.select("span[class=bigPrice price_withVat]");
        }
        if (elements.isEmpty()) {
            //TODO tu by mala byt vynimka ked bduem mat ze je nedostupne
            return null;
        }
        String text = elements.get(0).text();
        if (StringUtils.isBlank(text)) {
            return null;
        }
        StringBuffer sb = new StringBuffer(text);
        sb = sb.deleteCharAt(0);
        return new BigDecimal(sb.toString().replace(",", "."));
    }

    @Override
    protected ProductAction parseAction(Document document) {
        //TODO dorobit parsovanie zlavy v percentach
        boolean flag1 = ParserUtils.existElement(document, "span[class=icon-percentage icon]");
        boolean flag2 = ParserUtils.existElement(document, "span[class=quantityPercentDiscount icon-percentage icon]");
        return (flag1 || flag2) ? ProductAction.IN_ACTION : ProductAction.NON_ACTION;
    }

    @Override
    protected Date parseActionValidity(Document document) {
        //TODO
        return null;
    }

    @Override
    protected String parseProductName(Document document) {
        //TODO
        return null;
    }
}
