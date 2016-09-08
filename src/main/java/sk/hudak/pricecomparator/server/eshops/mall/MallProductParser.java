package sk.hudak.pricecomparator.server.eshops.mall;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.canonical.ProductAction;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractEshopProductParserNg;
import sk.hudak.pricecomparator.server.async.ng.impl.ParserUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 19. 7. 2016.
 */
public class MallProductParser extends AbstractEshopProductParserNg {

    @Override
    protected boolean isProductUnavailable(Document document) {
        return ParserUtils.notExistElement(document, "button[id=add-to-cart]");
    }

    @Override
    protected BigDecimal parsePriceForPackage(Document document) {
        Elements elements2 = document.select("b[class=pro-price con-emphasize font-primary--bold mr-5]");
        if (elements2.isEmpty()) {
            //TODO vynimka by mala byt...
            return null;
        }

        String cenaZaBalenie = elements2.get(0).text().replace("€", "").replace(",", ".").trim();
        return new BigDecimal(cenaZaBalenie);

    }

    @Override
    protected String parseProductName(Document document) {
        return null;
    }

    @Override
    protected ProductAction parseAction(Document document) {
        Elements elements3 = document.select("em[class=label label--action");
        if (!elements3.isEmpty()) {
            return ProductAction.IN_ACTION;
        } else {
            return ProductAction.NON_ACTION;
        }
    }

    @Override
    protected Date parseActionValidity(Document document) {
        return null;
    }
}
