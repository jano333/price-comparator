package sk.hudak.pricecomparator.server.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.api.model.EshopProductInfo;
import sk.hudak.pricecomparator.server.core.AbstractEshopProductInfo;
import sk.hudak.pricecomparator.server.core.AbstractEshopProductParser;

import java.math.BigDecimal;

/**
 * Created by jan on 7. 11. 2015.
 */
public class MallEshopProductParser extends AbstractEshopProductParser {
    @Override
    protected EshopProductInfo parsePrice(Document document) {
        //TODO aj akcia... tam je
        //https://www.mall.sk/plienky-pampers-3-6-kg/pampers-premium-care-plenky-2-mini-3-6-kg-216-ks?v=705110
        Elements elements = document.select("b[class=pro-price con-emphasize font-primary--bold lay-inline-block mr-5]");
        Element element = elements.get(0);
        final String cenaZaBalenie = element.text().replace("€", "").replace(",", ".").trim();

        return new AbstractEshopProductInfo(parserInputData) {

            @Override
            public BigDecimal getPriceForPackage() {
                return new BigDecimal(cenaZaBalenie);
            }
        };
    }
}
