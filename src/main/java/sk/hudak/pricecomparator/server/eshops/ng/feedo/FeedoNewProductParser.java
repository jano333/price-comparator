package sk.hudak.pricecomparator.server.eshops.ng.feedo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Map;

/**
 * Created by jan on 27. 12. 2016.
 */
public class FeedoNewProductParser {

    private static Logger logger = LoggerFactory.getLogger(FeedoNewProductParser.class);

    private static final int DEFAULT_TIMEOUT = 10000;

    //TODO toto domysliet
    private static final String MOZILLA_USER_AGENT_DEFAULT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.0";

    public void parserPage(String pageUrl) {
        try {
            logger.debug("conneting to: {}", pageUrl);

            Document doc = Jsoup.connect(pageUrl)
                    .userAgent(getUserAgent())
                    .cookies(getCookies())
                    .timeout(getTimeout())
                    .get();

            //iba pre feedo
            Elements elements = doc.select("article[class=box box-product]");
            //<article class="box box-product"
            for (Element element : elements) {
                NewProductInfoDto info = processOneProduct(element);
                System.out.println(info);
            }


        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    private NewProductInfoDto processOneProduct(Element element) {
        Element h1 = element.child(0);
        Element a = h1.child(0);
        String href = a.attr("href");
        String name = a.text();

        NewProductInfoDto result = new NewProductInfoDto();
        result.setProductName(name);
        result.setProductUrl(href);
        return result;
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

    public static void main(String[] args) {
        String searchKey = "pampers";

        // vyskladanie prvej stranky
        String pageUrl = "https://www.feedo.sk/vysledky-hladania/" + searchKey + "/";
        new FeedoNewProductParser().parserPage(pageUrl);


    }
}
