package sk.parser;

import sk.hudak.pricecomparator.middle.canonical.Unit;
import sk.hudak.pricecomparator.server.async.ng.EshopParserRequestNg;
import sk.hudak.pricecomparator.server.async.ng.EshopParserResponseNg;
import sk.hudak.pricecomparator.server.eshops.maderna.MadernaProductParser;

import java.math.BigDecimal;

/**
 * Created by jan on 11. 9. 2016.
 */
public class MadernaParserTest {

    public static void main(String[] args) {
        EshopParserRequestNg parserInputData = new EshopParserRequestNg()
                .setCountOfItemInOnePackage(76)
                .setUnit(Unit.KUS)
                .setCountOfUnit(new BigDecimal(1))
                .setEshopProductPage("http://www.maderna.sk/plienky/plienky-pampers-active-baby-dry-maxi-4-76ks/");

        MadernaProductParser parser = new MadernaProductParser();
        EshopParserResponseNg productInfo = parser.parseEshopProductInfo(parserInputData);
        System.out.println(productInfo);
    }
}
