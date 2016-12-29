package sk.hudak.pricecomparator.server.eshops.ng.feedo;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.hudak.pricecomparator.server.html.jsoup.JsoupNewProductParser;
import sk.hudak.pricecomparator.server.to.NewProductInfoDto;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jan on 27. 12. 2016.
 */
@Named
public class FeedoNewProductParser extends JsoupNewProductParser {

    private static Logger logger = LoggerFactory.getLogger(FeedoNewProductParser.class);

    @Override
    protected List<NewProductInfoDto> parseNewProducts(Document doc) {
        List<NewProductInfoDto> result = new ArrayList<>(24);
        Elements elements = doc.select("article[class=box box-product]");
        //<article class="box box-product"
        for (Element element : elements) {
            result.add(processOneProduct(element));
        }
        return result;
    }

    private NewProductInfoDto processOneProduct(Element element) {
        Element a = element.child(0).child(0);
        String href = a.attr("href");
        String name = a.text();
        Element div = element.child(1);
        String impUrl = div.select("div.box-image > a > img").get(0).attr("data-src");

        NewProductInfoDto result = new NewProductInfoDto();
        result.setProductName(name);
        result.setProductUrl(href);
        result.setProductPictureUrl(impUrl);
        return result;
    }

    public int getCountOfPages(String pageUrl) {
        Document doc = getDocument(pageUrl);
        Element element = doc.select("body > main > div > section > p > i").get(0);
        // "Na Váš zadaný výraz „pampers“ bolo nájdených 85 výsledkov."
        String text = element.text();
        text = text.substring(text.indexOf("nájdených ")/*, text.lastIndexOf(" ")*/);
        text = text.substring("nájdených ".length());
        text = text.substring(0, text.length() - 1 - "výsledkov.".length());

        int pocetZaznamov = Integer.valueOf(text);
        //feedo ma 24 itemov na jednu stranku
        double pocetStran = pocetZaznamov / 24.0;
        int tmp = (int) pocetStran;
        if (pocetStran > tmp) {
            tmp++;
        }
        return tmp;
    }
}
