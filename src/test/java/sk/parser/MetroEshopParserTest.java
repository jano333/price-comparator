package sk.parser;

import sk.hudak.pricecomparator.middle.canonical.Unit;
import sk.hudak.pricecomparator.server.async.ng.EshopParserRequest;
import sk.hudak.pricecomparator.server.async.ng.EshopParserResponse;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParser;
import sk.hudak.pricecomparator.server.eshops.metro.MetroProductParser;

import java.math.BigDecimal;

/**
 * Created by jan on 26. 10. 2016.
 */
public class MetroEshopParserTest {

    public static void main(String[] args) {

        EshopParserRequest parserInputData = new EshopParserRequest()
                .setUnit(Unit.KILOGRAM)
                .setCountOfUnit(new BigDecimal(0.8))
                .setCountOfItemInOnePackage(3)
                .setEshopProductPage("https://sortiment.metro.sk/sk/nutrilon-2-mp-24kg-3x800g/182540p/");

        EshopProductParser parser = new MetroProductParser();
        EshopParserResponse productInfo = parser.parseEshopProductInfo(parserInputData);
        System.out.println(productInfo);
    }

}
