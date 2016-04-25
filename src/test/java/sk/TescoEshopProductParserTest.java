package sk;

import sk.hudak.pricecomparator.middle.canonical.ParserInputData;
import sk.hudak.pricecomparator.middle.model.EshopProductInfo;
import sk.hudak.pricecomparator.server.eshops.tesco.TescoEshopProductParser;

/**
 * Created by jan on 24. 4. 2016.
 */
public class TescoEshopProductParserTest {

    public static void main(String[] args) {
//        ParserInputData parserInputData = new ParserInputData(
//                80,
//                Unit.DAVKA,
//                new BigDecimal(1),
//                "http://potravinydomov.itesco.sk/sk-SK/ProductDetail/ProductDetail/2002015116743");
        ParserInputData parserInputData = new ParserInputData(
                -1,
                null,
                null,
                "http://potravinydomov.itesco.sk/sk-SK/ProductDetail/ProductDetail/2002006571537");

        TescoEshopProductParser parser = new TescoEshopProductParser();
        EshopProductInfo productInfo = parser.getProductInfo(parserInputData);
        System.out.println(productInfo.getProductNameInEhop());
    }
}
