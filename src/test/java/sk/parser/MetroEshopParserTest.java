package sk.parser;

import sk.hudak.pricecomparator.middle.canonical.Unit;
import sk.hudak.pricecomparator.server.async.ng.EshopParserRequestNg;
import sk.hudak.pricecomparator.server.async.ng.EshopParserResponseNg;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParserNg;
import sk.hudak.pricecomparator.server.eshops.metro.MetroProductParser;

import java.math.BigDecimal;

/**
 * Created by jan on 26. 10. 2016.
 */
public class MetroEshopParserTest {

    public static void main(String[] args) {

        EshopParserRequestNg parserInputData = new EshopParserRequestNg()
                .setCountOfItemInOnePackage(1)
                .setUnit(Unit.LITER)
                .setCountOfUnit(new BigDecimal(1))
                .setEshopProductPage("https://sortiment.metro.sk/sk/absolut-vodka-40-1l-/159865p/");

        EshopProductParserNg parser = new MetroProductParser();
        EshopParserResponseNg productInfo = parser.parseEshopProductInfo(parserInputData);
        System.out.println(productInfo);
    }

}
