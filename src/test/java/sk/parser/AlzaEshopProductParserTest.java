package sk.parser;

import sk.hudak.pricecomparator.middle.canonical.Unit;
import sk.hudak.pricecomparator.server.tobedeleted.AlzaEshopProductParser;
import sk.hudak.pricecomparator.server.tobedeleted.EshopProductInfo;
import sk.hudak.pricecomparator.server.tobedeleted.ParserInputData;

import java.math.BigDecimal;

/**
 * Created by jan on 12. 11. 2015.
 */
@Deprecated
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
