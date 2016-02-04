package sk;

import sk.hudak.pricecomparator.client.ProductInfoToString;
import sk.hudak.pricecomparator.middle.canonical.ParserInputData;
import sk.hudak.pricecomparator.middle.canonical.Unit;
import sk.hudak.pricecomparator.middle.model.EshopProductInfo;
import sk.hudak.pricecomparator.server.parser.HravoZdravoProductParser;

import java.math.BigDecimal;

/**
 * Created by jan on 1. 1. 2016.
 */
public class HravoZdravoProductParserTest {

    public static void main(String[] args) {
        ParserInputData parserInputData = new ParserInputData(
                6,
                Unit.KUS,
                new BigDecimal(1),
                "https://www.hravo-zdravo.sk/produkt/nutrilon-1-6x800g");

        HravoZdravoProductParser parser = new HravoZdravoProductParser();
        EshopProductInfo productInfo = parser.getProductInfo(parserInputData);
        System.out.println(ProductInfoToString.toString(productInfo));
    }
}
