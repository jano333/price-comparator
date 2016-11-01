package sk.parser;

import sk.hudak.jef.ssl.JefSslManager;
import sk.hudak.pricecomparator.middle.canonical.Unit;
import sk.hudak.pricecomparator.server.async.ng.EshopParserRequest;
import sk.hudak.pricecomparator.server.async.ng.EshopParserResponse;
import sk.hudak.pricecomparator.server.eshops.hej.HejProductParser;

import java.math.BigDecimal;

/**
 * Created by jan on 30. 10. 2016.
 */
public class HejEshopProductParserTest {
    public static void main(String[] args) {

        JefSslManager.getInstance().init();

        EshopParserRequest parserInputData = new EshopParserRequest()
                .setCountOfItemInOnePackage(4)
                .setUnit(Unit.KUS)
                .setCountOfUnit(new BigDecimal(1))
                .setEshopProductPage("http://www.hej.sk/filter-na-vodu-brita-maxtra-208891/");

        HejProductParser parser = new HejProductParser();
        EshopParserResponse productInfo = parser.parseEshopProductInfo(parserInputData);
        System.out.println(productInfo);
    }
}
