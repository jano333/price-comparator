package sk.parser;

import sk.hudak.pricecomparator.client.ProductInfoToString;
import sk.hudak.pricecomparator.middle.canonical.Unit;
import sk.hudak.pricecomparator.server.tobedeleted.EshopProductInfo;
import sk.hudak.pricecomparator.server.tobedeleted.ParserInputData;
import sk.hudak.pricecomparator.server.tobedeleted.PilulkaEshopProductParser;

import java.math.BigDecimal;

/**
 * Created by jan on 29. 11. 2015.
 */
@Deprecated
public class PilulkaEshopProductParserTest {

    public static void main(String[] args) {
        ParserInputData parserInputData = new ParserInputData(
                80,
                Unit.KUS,
                new BigDecimal(1),
                "http://www.pilulka.sk/pampers-premium-care-2-mini-3-6-kg-80-ks");

        PilulkaEshopProductParser parser = new PilulkaEshopProductParser();
        EshopProductInfo productInfo = parser.getProductInfo(parserInputData);
        System.out.println(ProductInfoToString.toString(productInfo));
    }
}
