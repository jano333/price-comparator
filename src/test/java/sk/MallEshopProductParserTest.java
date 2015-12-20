package sk;

import sk.hudak.pricecomparator.middle.api.canonical.ParserInputData;
import sk.hudak.pricecomparator.middle.api.canonical.Unit;
import sk.hudak.pricecomparator.middle.api.model.EshopProductInfo;
import sk.hudak.pricecomparator.server.parser.MallEshopProductParser;

import java.math.BigDecimal;

/**
 * Created by jan on 7. 11. 2015.
 */
public class MallEshopProductParserTest {

    public static void main(String[] args) {
        ParserInputData parserInputData = new ParserInputData(
                60,
                Unit.KUS,
                new BigDecimal(2),
                "https://www.mall.sk/plienky-pampers-4-9-kg/pampers-plenky-premiumcare-3-midi-120-kss?v=536120");

        MallEshopProductParser parser = new MallEshopProductParser();
        EshopProductInfo productInfo = parser.getProductInfo(parserInputData);
        System.out.println(productInfo.toString());
    }
}
