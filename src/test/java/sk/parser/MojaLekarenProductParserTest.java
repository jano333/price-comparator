package sk.parser;

import sk.hudak.pricecomparator.middle.canonical.Unit;
import sk.hudak.pricecomparator.server.async.ng.EshopParserRequestNg;
import sk.hudak.pricecomparator.server.async.ng.EshopParserResponseNg;
import sk.hudak.pricecomparator.server.eshops.mojalekaren.MojalekarenProductParser;

import java.math.BigDecimal;

/**
 * Created by jan on 7. 10. 2016.
 */
public class MojaLekarenProductParserTest {
    public static void main(String[] args) {
        EshopParserRequestNg parserInputData = new EshopParserRequestNg()
                .setCountOfItemInOnePackage(6)
                .setUnit(Unit.KILOGRAM)
                .setCountOfUnit(new BigDecimal(0.8))
                .setEshopProductPage("https://www.mojalekaren.sk/nutrilon-3-pronutra-balenia-6x-800g/");

        MojalekarenProductParser parser = new MojalekarenProductParser();
        EshopParserResponseNg productInfo = parser.parseEshopProductInfo(parserInputData);
        System.out.println(productInfo);
    }
}
