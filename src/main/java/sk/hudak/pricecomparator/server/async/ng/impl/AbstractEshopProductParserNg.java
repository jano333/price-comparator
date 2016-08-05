package sk.hudak.pricecomparator.server.async.ng.impl;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.hudak.pricecomparator.middle.model.ProductAction;
import sk.hudak.pricecomparator.server.async.ng.EshopParserRequestNg;
import sk.hudak.pricecomparator.server.async.ng.EshopParserResponseNg;
import sk.hudak.pricecomparator.server.async.ng.EshopParserResponseNgFactory;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParserNg;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

/**
 * Created by jan on 9. 7. 2016.
 */
public abstract class AbstractEshopProductParserNg implements EshopProductParserNg {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private static final int DEFAULT_TIMEOUT = 10000;
    private static final String MOZILLA_USER_AGENT_DEFAULT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.0";

    protected EshopParserRequestNg request;

    @Override
    public EshopParserResponseNg parseEshopProductInfo(EshopParserRequestNg request) {
        this.request = request;

        try {
            logger.debug("conneting to: " + request.getEshopProductPage());

            Connection connection = Jsoup.connect(request.getEshopProductPage());
            connection.userAgent(getUserAgent());
            connection.cookies(getCookies());
            connection.timeout(getTimeout());

            Document document = connection.get();

            if (isProductUnavailable(document)) {
                logger.error("produkt nie je dostupny: {}", request.getEshopProductPage());
                return EshopParserResponseNgFactory.createUnaviable();
            }

            // nazov produktu
            String productName = parseProductName(document);

            // cena za balenie
            BigDecimal priceForPackage = parsePriceForPackage(document);

            // akcia
            ProductAction productAction = parseAction(document);

            // platnost akcie
            Date actionValidity = null;
            if (ProductAction.IN_ACTION.equals(productAction)) {
                actionValidity = parseActionValidity(document);
            }

            //TODO dokocit a posielat do navratovej hodnoty...
            String pictureUrl = parsePictureUrl(document);


            BigDecimal priceForOneItemInPackage = ProductPriceCalculator.calculatePriceForOneItemInPackage(
                    priceForPackage,
                    new BigDecimal(request.getCountOfItemInOnePackage()));

            BigDecimal priceForUnit = ProductPriceCalculator.calculatePriceForUnit(request.getUnit(),
                    request.getCountOfUnit(), priceForOneItemInPackage);


            EshopParserResponseNg responseNg = new EshopParserResponseNg()
                    .setProductName(productName)
                    .setPriceForPackage(priceForPackage)
                    .setPriceForOneItemInPackage(priceForOneItemInPackage)
                    .setPriceForUnit(priceForUnit)
                    .setAction(productAction)
                    .setActionValidTo(actionValidity);

            return responseNg;

        } catch (HttpStatusException e) {
            logger.error("http status code: " + e.getStatusCode());
            logger.error("error while conneting to: " + request.getEshopProductPage(), e);
            //TODO doriest dany pripad
            return EshopParserResponseNgFactory.createUnaviable();

        } catch (Exception e) {
            logger.error("another error while conneting to: " + request.getEshopProductPage(), e);
            //TODO doriest dany pripad
            return EshopParserResponseNgFactory.createUnaviable();
        }
    }

    protected String parsePictureUrl(Document document) {

        return null;
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

    protected abstract BigDecimal parsePriceForPackage(Document document);

    protected abstract String parseProductName(Document document);

    //TODO metody ohladne akcie zjednotit do jedne a vracat prisny objekt kde bude dalsie info !!!
    protected abstract ProductAction parseAction(Document document);

    protected abstract Date parseActionValidity(Document document);

    protected ProductAction parseAction(Document document, String selector){
        return ParserUtils.existElement(document, selector)
                ? ProductAction.IN_ACTION
                : ProductAction.NON_ACTION;
    }


}
