package sk.hudak.pricecomparator.server.eshops.alza;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.model.ProductAction;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractEshopProductParserNg;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 11. 7. 2016.
 */
public class AlzaEshopProductParserNg extends AbstractEshopProductParserNg {

    @Override
    protected boolean isProductUnavailable(Document document) {
        //TODO zatial neviem ako...
        return false;
    }

    @Override
    protected BigDecimal parsePriceForPackage(Document document) {
        Elements elements = document.select("span[class=bigPrice price_withVat]");
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
    protected String parseProductName(Document document) {
        return null;
    }

    @Override
    protected ProductAction parseAction(Document document) {
        return null;
    }

    @Override
    protected Date parseActionValidity(Document document) {
        return null;
    }
}
