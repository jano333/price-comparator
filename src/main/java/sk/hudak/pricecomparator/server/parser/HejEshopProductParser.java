package sk.hudak.pricecomparator.server.parser;

import org.jsoup.nodes.Document;
import sk.hudak.pricecomparator.middle.api.model.EshopProductInfo;
import sk.hudak.pricecomparator.server.core.AbstractEshopProductParser;
import sk.hudak.pricecomparator.server.core.AbstractEshopProductInfo;

import java.math.BigDecimal;

/**
 * Created by jan on 14. 10. 2015.
 */
public class HejEshopProductParser extends AbstractEshopProductParser {

    @Override
    protected EshopProductInfo parsePrice(Document document) {
        //        <span id="real_price">14.40</span>
        final String cenaZaBalenie = document.select("span#real_price").html();

        return new AbstractEshopProductInfo(parserInputData) {
            //TODO akcia?
            @Override
            public BigDecimal getPriceForPackage() {
                return new BigDecimal(cenaZaBalenie);
            }
        };
    }
}
