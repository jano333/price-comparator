package sk.parser;

import sk.hudak.pricecomparator.middle.canonical.Unit;
import sk.hudak.pricecomparator.server.async.ng.EshopParserRequest;
import sk.hudak.pricecomparator.server.async.ng.EshopParserResponse;
import sk.hudak.pricecomparator.server.tobedeleted.FeedoProductParser;

import java.math.BigDecimal;

/**
 * Created by jan on 26. 3. 2016.
 */
public class FeedoEshopProductParserTest {

    public static void main(String[] args) {
        EshopParserRequest parserInputData = new EshopParserRequest()
                .setCountOfItemInOnePackage(3)
                .setUnit(Unit.KUS)
                .setCountOfUnit(new BigDecimal(0.8))
                .setEshopProductPage("https://www.feedo.sk/3x-nutrilon-3-pronutra-800g-dojcenske-mlieko/");

        FeedoProductParser parser = new FeedoProductParser();
        EshopParserResponse productInfo = parser.parseEshopProductInfo(parserInputData);
        System.out.println(productInfo);

    }
}
