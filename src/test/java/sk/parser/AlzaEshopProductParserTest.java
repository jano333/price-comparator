package sk.parser;

import sk.hudak.pricecomparator.middle.canonical.Unit;
import sk.hudak.pricecomparator.server.async.ng.EshopParserRequest;
import sk.hudak.pricecomparator.server.async.ng.EshopParserResponse;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParser;
import sk.hudak.pricecomparator.server.eshops.alza.AlzaProductParser;

import java.math.BigDecimal;

/**
 * Created by jan on 12. 11. 2015.
 */
public class AlzaEshopProductParserTest {

    public static void main(String[] args) {
        EshopParserRequest parserInputData = new EshopParserRequest()
                .setUnit(Unit.KUS)
                .setCountOfUnit(new BigDecimal(4))
                .setCountOfItemInOnePackage(52)
                .setEshopProductPage("https://www.alza.sk/trendy/hipp-babysanft-ultra-sensitive-4x52-ks-d2643289.htm");

        EshopProductParser parser = new AlzaProductParser();
        EshopParserResponse productInfo = parser.parseEshopProductInfo(parserInputData);
        System.out.println(productInfo);
    }
}
