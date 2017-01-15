package sk.hudak.pricecomparator.server.eshops.ng.feedo;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.hudak.pricecomparator.middle.exeption.PriceComparatorException;
import sk.hudak.pricecomparator.server.html.jsoup.JsoupNewProductParser;
import sk.hudak.pricecomparator.server.to.NewProductCreateDto;

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
    protected List<NewProductCreateDto> parseNewProducts(Document doc) {
        List<NewProductCreateDto> result = new ArrayList<>(24);
        for (Element element : doc.select("article[class=box box-product]")) {
            result.add(processOneProduct(element));
        }
        return result;
    }

    private NewProductCreateDto processOneProduct(Element element) {
        Element a = element.child(0).child(0);
        String href = a.attr("href");
        String name = a.text();
        Element div = element.child(1);
        String impUrl = div.select("div.box-image > a > img").get(0).attr("data-src");

        NewProductCreateDto result = new NewProductCreateDto();
        result.setProductName(name);
        result.setProductUrl(href);
        result.setProductPictureUrl(impUrl);
        return result;
    }

    public int getCountOfPages(String pageUrl) {
        try {
            Document doc = getDocument(pageUrl);
            //„Pampers“ (85)
            Element element = doc.select("body > main > div > section > h1 > span").get(0);
            String text = element.text();
            text = text.substring(text.indexOf("(") + 1, text.indexOf(")"));

            int pocetZaznamov = Integer.valueOf(text);
            //feedo ma 24 itemov na jednu stranku
            double pocetStran = pocetZaznamov / 24.0;
            //zaokruhlenie nahor :-)
            int tmp = (int) pocetStran;
            if (pocetStran > tmp) {
                tmp++;
            }
            return tmp;
        } catch (Exception e) {
            //TODO bez toho som nevedel aka chyba je, nic v loggoch nebolo
            logger.error("error", e);
            throw new PriceComparatorException("erro while retrieving count of pages", e);
        }

    }
}
