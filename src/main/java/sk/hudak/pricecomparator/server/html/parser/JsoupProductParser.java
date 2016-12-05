package sk.hudak.pricecomparator.server.html.parser;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.hudak.pricecomparator.middle.canonical.ProductAction;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

/**
 * Created by jan on 3. 11. 2016.
 */
public abstract class JsoupProductParser implements HtmlProductParser {

    private static Logger logger = LoggerFactory.getLogger(JsoupProductParser.class);

    private static final int DEFAULT_TIMEOUT = 10000;

    //TODO toto domysliet
    private static final String MOZILLA_USER_AGENT_DEFAULT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.0";

    // flag, ak eshop neudava platnost do kedy plati akcia produktu
    public static final Date ACTION_VALIDITY_NOT_DEFINE = null;


    @Override
    public void parseProductPage(String productPage, ProductParserResultCallback callback) {
        //TODO vstupne validacie!!!
        try {
            logger.debug("conneting to: {}", productPage);

            Connection connection = Jsoup.connect(productPage);
            connection.userAgent(getUserAgent());
            connection.cookies(getCookies());
            connection.timeout(getTimeout());

            Document document = connection.get();

            if (isProductUnavailable(document)) {
                logger.error("produkt nie je dostupny: " + productPage);
                callback.onProductUnavailable(productPage);
                return;
            }

            ProductParserResultDto result = new ProductParserResultDto();
            // cena za balenie
            result.setProductPriceForPackage(parseProductPriceForPackage(document));
            // nazov
            result.setProductName(parseProductName(document));
            // akcia
            result.setProductAction(parseProductAction(document));
            // platnost akcie
            if (ProductAction.IN_ACTION.equals(result.getProductAction())) {
                result.setProductActionValidity(parseProductActionValidity(document));
            }
            // URL obrazku
            result.setProductPictureURL(parseProductPictureURL(document));

            callback.onSucces(result);

        } catch (Exception e) {
            logger.error(e.getMessage());

            //404 ak sa stranka nenasla
            if (e instanceof HttpStatusException) {
                HttpStatusException httpError = (HttpStatusException) e;
                if (httpError.getStatusCode() == 404) {
                    callback.onProductPageNotFound(productPage);
                    return;
                }
            }
            // interna chyba pri parsovani
            callback.onError(productPage, e);
        }
    }

    protected String getUserAgent() {
        //TODO rozne typy agentov
        return MOZILLA_USER_AGENT_DEFAULT;
    }

    protected int getTimeout() {
        return DEFAULT_TIMEOUT;
    }

    protected Map<String, String> getCookies() {
        return Collections.emptyMap();
    }

    protected abstract boolean isProductUnavailable(Document document);

    protected abstract BigDecimal parseProductPriceForPackage(Document document);

    //TODO metody ohladne akcie zjednotit do jedne a vracat prisny objekt kde bude dalsie info !!!
    protected abstract ProductAction parseProductAction(Document document);

    protected abstract Date parseProductActionValidity(Document document);

    protected abstract String parseProductName(Document document);

    protected abstract String parseProductPictureURL(Document document);
}
