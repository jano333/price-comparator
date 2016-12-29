package sk.hudak.pricecomparator.server.html.jsoup;

import org.jsoup.nodes.Document;
import sk.hudak.pricecomparator.middle.canonical.ProductAction;

/**
 * Created by jan on 6. 11. 2016.
 */
public class JsoupParserUtils {

    public static boolean notExistElement(Document document, String cssQuery) {
        return document.select(cssQuery).isEmpty();
    }

    public static boolean existElement(Document document, String cssQuery) {
        return !notExistElement(document, cssQuery);
    }

    public static ProductAction parseAction(Document document, String selector) {
        return existElement(document, selector) ? ProductAction.IN_ACTION : ProductAction.NON_ACTION;
    }
}
