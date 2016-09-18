package sk.parser;

import sk.hudak.pricecomparator.middle.canonical.Unit;
import sk.hudak.pricecomparator.server.async.ng.EshopParserRequestNg;
import sk.hudak.pricecomparator.server.async.ng.EshopParserResponseNg;
import sk.hudak.pricecomparator.server.eshops.alza.AlzaProductParser;

import java.math.BigDecimal;

/**
 * Created by jan on 12. 11. 2015.
 */
public class AlzaEshopProductParserTest {
    public static void main(String[] args) {
        EshopParserRequestNg parserInputData = new EshopParserRequestNg()
                .setCountOfItemInOnePackage(1)
                .setUnit(Unit.KUS)
                .setCountOfUnit(new BigDecimal(1))
                .setEshopProductPage("https://www.alza.sk/maxi/philips-avent-elektronicka-odsavacka-natural-d4191492.htm");

        AlzaProductParser parser = new AlzaProductParser();
        EshopParserResponseNg productInfo = parser.parseEshopProductInfo(parserInputData);
        System.out.println(productInfo);
    }
}
