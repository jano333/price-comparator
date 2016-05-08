package sk.hudak.pricecomparator.server.core;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.hudak.pricecomparator.middle.EshopProductParser;
import sk.hudak.pricecomparator.middle.canonical.ParserInputData;
import sk.hudak.pricecomparator.middle.model.EshopProductInfo;
import sk.hudak.pricecomparator.server.factory.ProductInfoFactory;

import java.util.Collections;
import java.util.Map;

/**
 * Created by jan on 13. 10. 2015.
 */
public abstract class AbstractEshopProductParser implements EshopProductParser {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private static final int DEFAULT_TIMEOUT = 10000;
    private static final String MOZILLA_USER_AGENT_DEFAULT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.0";

    protected ParserInputData parserInputData;

    protected abstract EshopProductInfo parsePrice(Document document);

    @Override
    public EshopProductInfo getProductInfo(ParserInputData parserInputData) {
        this.parserInputData = parserInputData;

        try {
            logger.debug("conneting to: " + parserInputData.getEshopProductPage());
            Connection connection = Jsoup.connect(parserInputData.getEshopProductPage());
            connection.userAgent(getUserAgent());
            connection.cookies(getCookies());
            connection.timeout(getTimeout());

            Document doc = connection.get();


            return parsePrice(doc);

        } catch (HttpStatusException e) {
            // ak hodi napr 404 tak vratit ze je nedostupne
            logger.error("http error  while conneting to: " + parserInputData.getEshopProductPage(), e);
            return ProductInfoFactory.createUnaviable();

        } catch (Exception e) {
            logger.error("another error while conneting to: " + parserInputData.getEshopProductPage(), e);
            return ProductInfoFactory.createUnaviable();
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

    protected boolean existElement(Document document, String cssQuery) {
        return document.select(cssQuery).isEmpty();
    }

    /**
     * Odstrani <code>count</code> znakov z konca retazca <code>str</code>.
     *
     * @param count
     */
    protected String removeLastCharacters(String str, int count) {
        if (StringUtils.isBlank(str)) {
            return str;
        }
        if (count < 1) {
            return str;
        }
        StringBuffer sb = new StringBuffer(str);
        for (int i = 0; i < count; i++) {
            sb = sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
