package sk;

import sk.hudak.pricecomparator.middle.api.canonical.ParserInputData;
import sk.hudak.pricecomparator.middle.api.canonical.Unit;
import sk.hudak.pricecomparator.middle.api.model.EshopProductInfo;
import sk.hudak.pricecomparator.server.parser.SemistorEshopProductParser;

import java.math.BigDecimal;

/**
 * Created by jan on 7. 11. 2015.
 */
public class SemistorEshopProductParserTest {

    public static void main(String[] args) {
        ParserInputData parserInputData = new ParserInputData(
                80,
                Unit.KUS,
                new BigDecimal(1),
                "http://semilton.sk/baby/health-hygiene/diapers/pampers-premium-vp-mini-80/");


        SemistorEshopProductParser parser = new SemistorEshopProductParser();
        EshopProductInfo productInfo = parser.getProductInfo(parserInputData);

    }
}
