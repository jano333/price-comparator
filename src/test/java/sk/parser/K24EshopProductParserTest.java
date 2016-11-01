package sk.parser;

import sk.hudak.pricecomparator.middle.canonical.Unit;
import sk.hudak.pricecomparator.server.async.ng.EshopParserRequest;
import sk.hudak.pricecomparator.server.async.ng.EshopParserResponse;
import sk.hudak.pricecomparator.server.eshops.k24.K24ProductParser;

import java.math.BigDecimal;

/**
 * Created by jan on 29. 11. 2015.
 */
public class K24EshopProductParserTest {
    public static void main(String[] args) {
        EshopParserRequest parserInputData = new EshopParserRequest()
                .setCountOfItemInOnePackage(80)
                .setUnit(Unit.KUS)
                .setCountOfUnit(new BigDecimal(1))
                .setEshopProductPage("http://www.k24.sk/product/288078/Elektronika/Sport_a_hobby/Pampers_Premium_Care_VP_Mini_80_ks_.html");

        K24ProductParser parser = new K24ProductParser();
        EshopParserResponse productInfo = parser.parseEshopProductInfo(parserInputData);
        System.out.println(productInfo);
    }
}
