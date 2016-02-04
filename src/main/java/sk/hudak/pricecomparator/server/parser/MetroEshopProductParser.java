package sk.hudak.pricecomparator.server.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.model.EshopProductInfo;
import sk.hudak.pricecomparator.middle.model.ProductAction;
import sk.hudak.pricecomparator.server.core.AbstractEshopProductParser;
import sk.hudak.pricecomparator.server.core.EshopProductInfoDefault;
import sk.hudak.pricecomparator.server.factory.ProductInfoFactory;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;

/**
 * Created by jan on 13. 10. 2015.
 */
public class MetroEshopProductParser extends AbstractEshopProductParser {

    @Override
    protected Map<String, String> getCookies() {
        // 21 su Kosice pobocka
        return Collections.singletonMap("storeId", "21");
    }

    @Override
    protected EshopProductInfo parsePrice(Document document) {
        boolean nedostupne = document.select("div[class=action-ns]").size() > 0;
        if (nedostupne) {
            return ProductInfoFactory.createUnaviable();
        }
        Elements elements = document.select("td[class=text-right]:has(strong)");
        boolean action = document.select("div[class=action-a]").size() > 0;

        EshopProductInfoDefault productInfo = new EshopProductInfoDefault();
        productInfo.setAction(action ? ProductAction.IN_ACTION : ProductAction.NON_ACTION);
        productInfo.setPriceForPackage(parseCena(elements.get(0)));
        productInfo.setPriceForJednotka(parseCena(elements.get(1)));
        productInfo.setPriceForItemInPackage(parseCena(elements.get(2)));
        return productInfo;
    }

    protected BigDecimal parseCena(Element element) {
        String html = element.html();
        int beginIndex = html.indexOf("|") + 2;
        int endIndex = html.indexOf("s DPH") - 2;
        html = html.substring(beginIndex, endIndex).trim();
        return new BigDecimal(html.replace(",", "."));
    }


}
