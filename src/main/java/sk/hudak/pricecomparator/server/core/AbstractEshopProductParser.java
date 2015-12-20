package sk.hudak.pricecomparator.server.core;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import sk.hudak.pricecomparator.middle.api.EshopProductParser;
import sk.hudak.pricecomparator.middle.api.canonical.ParserInputData;
import sk.hudak.pricecomparator.middle.api.model.EshopProductInfo;
import sk.hudak.pricecomparator.server.factory.ProductInfoFactory;

import java.util.Collections;
import java.util.Map;

/**
 * Created by jan on 13. 10. 2015.
 */
public abstract class AbstractEshopProductParser implements EshopProductParser {

    private static final int DEFAULT_TIMEOUT = 12000;
    private static final String MOZILLA_USER_AGENT_DEFAULT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.0";

    protected ParserInputData parserInputData;

    protected abstract EshopProductInfo parsePrice(Document document);

    @Override
    public EshopProductInfo getProductInfo(ParserInputData parserInputData) {
        this.parserInputData = parserInputData;

        try {
            System.out.println("conneting to: " + parserInputData.getEshopProductPage());
            Connection connection = Jsoup.connect(parserInputData.getEshopProductPage());
            connection.userAgent(getUserAgent());
            connection.cookies(getCookies());
            connection.timeout(getTimeout());

            Document doc = connection.get();


            return parsePrice(doc);

        } catch (HttpStatusException e) {
            // ak hodi napr 404 tak vratit ze je nedostupne
            e.printStackTrace();
            return ProductInfoFactory.createUnaviable();

        } catch (Exception e) {
            System.out.println("error while conneting to: " + parserInputData.getEshopProductPage());
            //TODO
            e.printStackTrace();
            return null;
        }

    }


    protected String getUserAgent() {
        return MOZILLA_USER_AGENT_DEFAULT;
    }

    protected int getTimeout() {
        return DEFAULT_TIMEOUT;
    }

    protected Map<String, String> getCookies() {
        return Collections.emptyMap();
    }

}
