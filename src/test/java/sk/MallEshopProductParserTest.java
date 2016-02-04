package sk;

import sk.hudak.pricecomparator.middle.canonical.ParserInputData;
import sk.hudak.pricecomparator.middle.canonical.Unit;
import sk.hudak.pricecomparator.middle.model.EshopProductInfo;
import sk.hudak.pricecomparator.server.eshops.mall.MallEshopProductParser;

import java.math.BigDecimal;

/**
 * Created by jan on 7. 11. 2015.
 */
public class MallEshopProductParserTest {

    public static void main(String[] args) {
        ParserInputData parserInputData = new ParserInputData(
                6,
                Unit.KILOGRAM,
                new BigDecimal(0.8),
                "https://www.mall.sk/detske-mlieka/nutrilon-2-6-x-800g");

        MallEshopProductParser parser = new MallEshopProductParser();
        EshopProductInfo productInfo = parser.getProductInfo(parserInputData);
        System.out.println(productInfo.getAction());
        System.out.println(productInfo.getActionValidTo());

        System.out.println(productInfo.toString());
    }
}
