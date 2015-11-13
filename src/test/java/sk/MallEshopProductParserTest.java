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
                160,
                Unit.KUS,
                new BigDecimal(1),
                "https://www.mall.sk/plienky-pampers-3-6-kg/pampers-premium-care-plenky-2-mini-3-6-kg-216-ks?v=705110");

        MallEshopProductParser parser = new MallEshopProductParser();
        EshopProductInfo productInfo = parser.getProductInfo(parserInputData);
        System.out.println("");
    }
}
