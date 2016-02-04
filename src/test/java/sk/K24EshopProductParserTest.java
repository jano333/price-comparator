package sk;

import sk.hudak.pricecomparator.client.ProductInfoToString;
import sk.hudak.pricecomparator.middle.canonical.ParserInputData;
import sk.hudak.pricecomparator.middle.canonical.Unit;
import sk.hudak.pricecomparator.middle.model.EshopProductInfo;
import sk.hudak.pricecomparator.server.parser.K24EshopProductParser;

import java.math.BigDecimal;

/**
 * Created by jan on 29. 11. 2015.
 */
public class K24EshopProductParserTest {
    public static void main(String[] args) {
        ParserInputData parserInputData = new ParserInputData(
                80,
                Unit.KUS,
                new BigDecimal(1),
                "http://www.k24.sk/product/288078/Elektronika/Sport_a_hobby/Pampers_Premium_Care_VP_Mini_80_ks_.html");

        K24EshopProductParser parser = new K24EshopProductParser();
        EshopProductInfo productInfo = parser.getProductInfo(parserInputData);
        System.out.println(ProductInfoToString.toString(productInfo));
    }
}
