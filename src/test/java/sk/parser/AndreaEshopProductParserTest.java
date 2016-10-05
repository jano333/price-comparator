package sk.parser;

import sk.hudak.pricecomparator.middle.canonical.Unit;
import sk.hudak.pricecomparator.server.async.ng.EshopParserRequestNg;
import sk.hudak.pricecomparator.server.async.ng.EshopParserResponseNg;
import sk.hudak.pricecomparator.server.eshops.andrea.AndreaProductParser;

import java.math.BigDecimal;

/**
 * Created by jan on 5. 10. 2016.
 */
public class AndreaEshopProductParserTest {

    public static void main(String[] args) {

        EshopParserRequestNg parserInputData = new EshopParserRequestNg()
                .setCountOfItemInOnePackage(120)
                .setUnit(Unit.KUS)
                .setCountOfUnit(new BigDecimal(1))
                .setEshopProductPage("https://www.andreashop.sk/pampers-premium-care-s3-120-ks-465461");

        AndreaProductParser parser = new AndreaProductParser();
        EshopParserResponseNg productInfo = parser.parseEshopProductInfo(parserInputData);
        System.out.println(productInfo);
    }
}
