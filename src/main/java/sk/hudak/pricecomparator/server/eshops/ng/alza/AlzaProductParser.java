package sk.hudak.pricecomparator.server.eshops.ng.alza;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.hudak.pricecomparator.middle.canonical.ProductAction;
import sk.hudak.pricecomparator.server.async.ng.impl.ParserUtils;
import sk.hudak.pricecomparator.server.html.jsoup.JsoupParserUtils;
import sk.hudak.pricecomparator.server.html.jsoup.JsoupProductParser;

import javax.inject.Named;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 2. 2. 2017.
 */
@Named
public class AlzaProductParser extends JsoupProductParser {

    private static Logger logger = LoggerFactory.getLogger(AlzaProductParser.class);

    @Override
    protected boolean isProductUnavailable(Document document) {
        return JsoupParserUtils.notExistElement(document, "a[class=cart-insert]")
                && JsoupParserUtils.notExistElement(document, "a[class=btnx normal green buy]");
    }

    @Override
    protected BigDecimal parseProductPriceForPackage(Document document) {
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
    protected ProductAction parseProductAction(Document document) {
        //TODO dorobit parsovanie zlavy v percentach
        boolean flag1 = ParserUtils.existElement(document, "span[class=icon-percentage icon]");
        boolean flag2 = ParserUtils.existElement(document, "span[class=quantityPercentDiscount icon-percentage icon]");
        return (flag1 || flag2) ? ProductAction.IN_ACTION : ProductAction.NON_ACTION;
    }

    @Override
    protected Date parseProductActionValidity(Document document) {
        //TODO
        return null;
    }

    @Override
    protected String parseProductName(Document document) {
//        return document.select("#h1c > h1").get(0).text();
//        return document.select("#h1c h1").get(0).text();
        return null;
    }

    @Override
    protected String parseProductPictureURL(Document document) {
//        return document.select("#imgMain").get(0).attr("src");
        return null;
    }
}
