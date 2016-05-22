package sk.hudak.entity.test;

import sk.hudak.pricecomparator.client.ProductInfoToString;
import sk.hudak.pricecomparator.middle.canonical.ParserInputData;
import sk.hudak.pricecomparator.middle.canonical.Unit;
import sk.hudak.pricecomparator.middle.model.EshopProductInfo;
import sk.hudak.pricecomparator.server.eshops.bugy.BugyEshopProductParser;

import java.math.BigDecimal;

/**
 * Created by jan on 22. 5. 2016.
 */
public class BugyEshopProductParserTest {

    public static void main(String[] args) {
        ParserInputData parserInputData = new ParserInputData(
                90,
                Unit.KUS,
                new BigDecimal(1),
                "http://www.bugy.sk/static/produkt/32474/PAMPERS-Active-Baby-3-Midi-Plienky-pre-deti-od-4-do-9kg-90ks/");

        BugyEshopProductParser parser = new BugyEshopProductParser();
        EshopProductInfo productInfo = parser.getProductInfo(parserInputData);
        System.out.println(ProductInfoToString.toString(productInfo));
    }
}
