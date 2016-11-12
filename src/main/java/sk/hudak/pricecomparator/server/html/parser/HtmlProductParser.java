package sk.hudak.pricecomparator.server.html.parser;

/**
 * Created by jan on 3. 11. 2016.
 */
public interface HtmlProductParser {

    void parseProductPage(String productPage, ProductParserResultCallback callback);
}
