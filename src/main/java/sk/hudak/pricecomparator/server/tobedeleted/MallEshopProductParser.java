package sk.hudak.pricecomparator.server.tobedeleted;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.canonical.ProductAction;

import java.math.BigDecimal;

/**
 * Created by jan on 7. 11. 2015.
 */
@Deprecated
public class MallEshopProductParser extends AbstractEshopProductParser {
    @Override
    protected EshopProductInfo parsePrice(Document document) {
        // ak tam je stitok "Už sa nepredáva"
        // vratit ako nedostupne
        Elements elements1 = document.select("em[class=label label--nostock]");
        if (!elements1.isEmpty()) {
            logger.error("Produkt sa uz nepredava");
            return ProductInfoFactory.createUnaviable();
        }

        Elements elements2 = document.select("b[class=pro-price con-emphasize font-primary--bold mr-5]");
        if (elements2.isEmpty()) {
            return ProductInfoFactory.createUnaviable();
        }

        final String cenaZaBalenie;
        try {
            cenaZaBalenie = elements2.get(0).text().replace("€", "").replace(",", ".").trim();
            if (StringUtils.isBlank(cenaZaBalenie)) {
                return ProductInfoFactory.createUnaviable();
            }

        } catch (Exception e) {
            //FIXME ulozit do DB integracny log
            e.printStackTrace();
            return ProductInfoFactory.createUnaviable();
        }

        // akcia
        final ProductAction productAction;
        Elements elements3 = document.select("em[class=label label--action");
        if (!elements3.isEmpty()) {
            productAction = ProductAction.IN_ACTION;
        } else {
            productAction = ProductAction.NON_ACTION;
        }

        return new AbstractEshopProductInfo(parserInputData) {

            @Override
            public BigDecimal getPriceForPackage() {
                return new BigDecimal(cenaZaBalenie);
            }

            @Override
            public ProductAction getAction() {
                return productAction;
            }
        };
    }
}
