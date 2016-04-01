package sk;

import sk.hudak.pricecomparator.client.ProductInfoToString;
import sk.hudak.pricecomparator.middle.canonical.ParserInputData;
import sk.hudak.pricecomparator.middle.canonical.Unit;
import sk.hudak.pricecomparator.middle.model.EshopProductInfo;
import sk.hudak.pricecomparator.server.eshops.feedo.FeedoEshopProductParser;
import sk.hudak.pricecomparator.server.eshops.pilulka.PilulkaEshopProductParser;

import java.math.BigDecimal;

/**
 * Created by jan on 26. 3. 2016.
 */
public class FeedoEshopProductParserTest {

    public static void main(String[] args) {
        ParserInputData parserInputData = new ParserInputData(
                6,
                Unit.KUS,
                new BigDecimal(0.8),
                "https://www.feedo.sk/6x-nutrilon-2-pronutra-800g-dojcenske-mlieko/");

        FeedoEshopProductParser parser = new FeedoEshopProductParser();
        EshopProductInfo productInfo = parser.getProductInfo(parserInputData);
        System.out.println(ProductInfoToString.toString(productInfo));

    }
}
