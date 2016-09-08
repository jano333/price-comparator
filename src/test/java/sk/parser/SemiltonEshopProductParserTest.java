package sk.parser;

import sk.hudak.pricecomparator.middle.canonical.Unit;
import sk.hudak.pricecomparator.server.async.ng.EshopParserRequestNg;
import sk.hudak.pricecomparator.server.async.ng.EshopParserResponseNg;
import sk.hudak.pricecomparator.server.eshops.semistor.SemiltonProductParser;

import java.math.BigDecimal;

/**
 * Created by jan on 7. 11. 2015.
 */
public class SemiltonEshopProductParserTest {

    public static void main(String[] args) {
        EshopParserRequestNg parserInputData = new EshopParserRequestNg()
                .setCountOfItemInOnePackage(147)
                .setUnit(Unit.KUS)
                .setCountOfUnit(new BigDecimal(1))
                .setEshopProductPage("http://semilton.sk/baby/health-hygiene/diapers/737599/");


        SemiltonProductParser parser = new SemiltonProductParser();
        EshopParserResponseNg productInfo = parser.parseEshopProductInfo(parserInputData);
        System.out.println(productInfo);

    }
}
