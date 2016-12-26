package sk.hudak.pricecomparator.server.eshops.ng.bambino;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.hudak.pricecomparator.middle.canonical.ProductAction;
import sk.hudak.pricecomparator.server.html.parser.JsoupParserUtils;
import sk.hudak.pricecomparator.server.html.parser.JsoupProductParser;

import javax.inject.Named;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 26. 12. 2016.
 */
@Named
public class BambinoProductParser extends JsoupProductParser {

    private static Logger logger = LoggerFactory.getLogger(BambinoProductParser.class);

    @Override
    protected boolean isProductUnavailable(Document document) {
        return JsoupParserUtils.notExistElement(document, "button[class=button button--primary add-to-cart]");
    }

    @Override
    protected BigDecimal parseProductPriceForPackage(Document document) {
        //cena
        Elements elements = document.select("strong[itemprop=price]");
        StringBuffer sb = new StringBuffer(elements.get(0).text());
        sb = sb.deleteCharAt(0);
        sb = sb.deleteCharAt(0);
        final String cenaZaBalenie = sb.toString().replace(",", ".");

        return new BigDecimal(cenaZaBalenie);
    }

    @Override
    protected ProductAction parseProductAction(Document document) {
        return JsoupParserUtils.existElement(document, "div[class=badge badge--discount]")
                ? ProductAction.IN_ACTION
                : ProductAction.NON_ACTION;
    }

    @Override
    protected Date parseProductActionValidity(Document document) {
        return ACTION_VALIDITY_NOT_DEFINE;
    }

    @Override
    protected String parseProductName(Document document) {
        //TODO
        return null;
    }

    @Override
    protected String parseProductPictureURL(Document document) {
        return document.select("img[itemprop=image]").get(0).attr("src");
    }
}
