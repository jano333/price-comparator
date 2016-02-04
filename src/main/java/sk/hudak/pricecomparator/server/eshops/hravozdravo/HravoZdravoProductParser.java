package sk.hudak.pricecomparator.server.eshops.hravozdravo;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.model.EshopProductInfo;
import sk.hudak.pricecomparator.server.core.AbstractEshopProductInfo;
import sk.hudak.pricecomparator.server.core.AbstractEshopProductParser;

import java.math.BigDecimal;

/**
 * Created by jan on 1. 1. 2016.
 */
public class HravoZdravoProductParser extends AbstractEshopProductParser {

    @Override
    protected EshopProductInfo parsePrice(Document document) {
        //TODO osetrit ...
        Elements elements = document.select("li[class=price blue]");
        TextNode node = (TextNode) elements.get(0).childNode(2);
        String wholeText = node.getWholeText();
        String tmp1 = wholeText.substring(0, wholeText.indexOf("€")).replace(",", ".");
        final String result = tmp1.substring(0, tmp1.length() - 1);


        return new AbstractEshopProductInfo(parserInputData) {

            @Override
            public BigDecimal getPriceForPackage() {
                return new BigDecimal(result);
            }
        };
    }
}
