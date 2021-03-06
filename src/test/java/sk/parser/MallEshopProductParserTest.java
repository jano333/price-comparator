package sk.parser;

import sk.hudak.pricecomparator.middle.canonical.Unit;
import sk.hudak.pricecomparator.server.async.ng.EshopParserRequest;
import sk.hudak.pricecomparator.server.async.ng.EshopParserResponse;
import sk.hudak.pricecomparator.server.eshops.mall.MallProductParser;

import java.math.BigDecimal;

/**
 * Created by jan on 7. 11. 2015.
 */

public class MallEshopProductParserTest {

    public static void main(String[] args) {
        EshopParserRequest parserInputData = new EshopParserRequest()
                .setCountOfItemInOnePackage(6)
                .setUnit(Unit.KILOGRAM)
                .setCountOfUnit(new BigDecimal(0.8))
                .setEshopProductPage("https://www.mall.sk/detske-mlieka/nutrilon-2-6-x-800g");

        MallProductParser parser = new MallProductParser();
        EshopParserResponse productInfo = parser.parseEshopProductInfo(parserInputData);
        System.out.println(productInfo);
    }
}
