package sk;

import sk.hudak.pricecomparator.middle.canonical.Unit;
import sk.hudak.pricecomparator.server.async.ng.EshopParserRequestNg;
import sk.hudak.pricecomparator.server.async.ng.EshopParserResponseNg;
import sk.hudak.pricecomparator.server.eshops.feedo.FeedoProductParser;

import java.math.BigDecimal;

/**
 * Created by jan on 26. 3. 2016.
 */
public class FeedoEshopProductParserTest {

    public static void main(String[] args) {
        EshopParserRequestNg parserInputData = new EshopParserRequestNg()
                .setCountOfItemInOnePackage(3)
                .setUnit(Unit.KUS)
                .setCountOfUnit(new BigDecimal(0.8))
                .setEshopProductPage("https://www.feedo.sk/3x-nutrilon-3-pronutra-800g-dojcenske-mlieko/");

        FeedoProductParser parser = new FeedoProductParser();
        EshopParserResponseNg productInfo = parser.parseEshopProductInfo(parserInputData);
        System.out.println(productInfo);

    }
}
