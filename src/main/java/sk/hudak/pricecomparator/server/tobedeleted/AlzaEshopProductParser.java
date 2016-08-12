package sk.hudak.pricecomparator.server.tobedeleted;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.math.BigDecimal;

/**
 * Created by jan on 12. 11. 2015.
 */
@Deprecated
public class AlzaEshopProductParser extends AbstractEshopProductParser {


    @Override
    protected EshopProductInfo parsePrice(Document document) {
        //ta kto to nie
        //<span class="bigPrice price_withVat">€16,64</span>
        final String cenaZaBalenie = parseCenaZaBalenie(document);

        if (StringUtils.isBlank(cenaZaBalenie)) {
            return ProductInfoFactory.createUnaviable();
        }

        final String productImageUrl = parseProductImageUrl(document);

        return new AbstractEshopProductInfo(parserInputData) {
            @Override
            public BigDecimal getPriceForPackage() {
                return new BigDecimal(cenaZaBalenie);
            }

            @Override
            public String getProductImageUrl() {
                return productImageUrl;
            }
        };
    }

    private String parseCenaZaBalenie(Document document) {
        Elements elements = document.select("span[class=bigPrice price_withVat]");
        if (elements.isEmpty()) {
            return null;
        }
        String text = elements.get(0).text();
        if (StringUtils.isBlank(text)) {
            return null;
        }
        StringBuffer sb = new StringBuffer(text);
        sb = sb.deleteCharAt(0);
        return sb.toString().replace(",", ".");
    }

    private String parseProductImageUrl(Document document) {
        Elements elements = document.select("img[id=imgMain]");
        if (elements.isEmpty()) {
            return null;
        }
        Element element = elements.get(0);
        String src = element.attributes().get("src");
        return src;
    }


}
