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

    //TODo remove
    public static void main(String[] args) {
//        String searchKey = "pampers";
        String searchKey = "nutrilon";

        // vyskladanie prvej stranky
        String pageUrl = "https://www.feedo.sk/vysledky-hladania/" + searchKey + "/";
        new FeedoNewProductParser().parserPage(pageUrl);


    }
}
