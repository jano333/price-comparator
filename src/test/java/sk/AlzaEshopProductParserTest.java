package sk;

import sk.hudak.pricecomparator.middle.api.canonical.ParserInputData;
import sk.hudak.pricecomparator.middle.api.canonical.Unit;
import sk.hudak.pricecomparator.middle.api.model.EshopProductInfo;
import sk.hudak.pricecomparator.server.parser.AlzaEshopProductParser;

import java.math.BigDecimal;

/**
 * Created by jan on 12. 11. 2015.
 */
public class AlzaEshopProductParserTest {
    public static void main(String[] args) {
        ParserInputData parserInputData = new ParserInputData(
                80,
                Unit.DAVKA,
                new BigDecimal(1),
                "https://www.alza.sk/maxi/ariel-mountain-spring-6-kg-80-pranie-d2643269.htm");

        AlzaEshopProductParser parser = new AlzaEshopProductParser();
        EshopProductInfo productInfo = parser.getProductInfo(parserInputData);
        System.out.println("");
    }
}
