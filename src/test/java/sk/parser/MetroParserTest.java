package sk.parser;

import sk.hudak.pricecomparator.middle.canonical.Unit;
import sk.hudak.pricecomparator.server.tobedeleted.EshopProductInfo;
import sk.hudak.pricecomparator.server.tobedeleted.MetroEshopProductParserOld;
import sk.hudak.pricecomparator.server.tobedeleted.ParserInputData;

import java.math.BigDecimal;

/**
 * Created by jan on 30. 6. 2016.
 */
@Deprecated
public class MetroParserTest {

    public static void main(String[] args) {
        ParserInputData parserInputData = new ParserInputData(
                3,
                Unit.KILOGRAM,
                new BigDecimal(0.8),
                "https://sortiment.metro.sk/sk/nutrilon-2-pronutra-24kg3x800g/226739p/");

        MetroEshopProductParserOld parser = new MetroEshopProductParserOld();
        EshopProductInfo productInfo = parser.getProductInfo(parserInputData);
        System.out.println(productInfo.getAction());
        System.out.println(productInfo.getActionValidTo());

        System.out.println(productInfo.toString());
    }
}
