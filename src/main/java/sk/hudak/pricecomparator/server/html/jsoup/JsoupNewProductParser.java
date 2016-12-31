package sk.hudak.pricecomparator.server.html.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.hudak.pricecomparator.middle.exeption.PriceComparatorException;
import sk.hudak.pricecomparator.server.to.NewProductCreateDto;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by jan on 28. 12. 2016.
 */
public abstract class JsoupNewProductParser {

    private static Logger logger = LoggerFactory.getLogger(JsoupNewProductParser.class);

    private static final int DEFAULT_TIMEOUT = 10000;
    //TODO toto domysliet
    private static final String MOZILLA_USER_AGENT_DEFAULT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.0";

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

    public List<NewProductCreateDto> parserPage(String pageUrl) {
        try {
            logger.debug("conneting to: {}", pageUrl);
            Document doc = getDocument(pageUrl);

            return parseNewProducts(doc);

        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    protected Document getDocument(String pageUrl) {
        try {
            return Jsoup.connect(pageUrl)
                    .userAgent(getUserAgent())
                    .cookies(getCookies())
                    .timeout(getTimeout())
                    .get();
        } catch (IOException e) {
            throw new PriceComparatorException("error while retrieving document", e);
        }
    }

    protected abstract List<NewProductCreateDto> parseNewProducts(Document doc);
}
