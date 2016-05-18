package sk;

import sk.hudak.jef.ssl.JefSslManager;
import sk.hudak.pricecomparator.middle.canonical.ParserInputData;
import sk.hudak.pricecomparator.middle.canonical.Unit;
import sk.hudak.pricecomparator.middle.model.EshopProductInfo;
import sk.hudak.pricecomparator.server.eshops.hej.HejEshopProductParser;

import java.math.BigDecimal;

/**
 * Created by jan on 18. 5. 2016.
 */
public class SslFixTest {

    // http://www.hej.sk/plienky-pampers-active-baby-dry-vel-3-midi-90ks/
    public static void main(String[] args) {
        JefSslManager.getInstance().init();


        ParserInputData parserInputData = new ParserInputData(
                90,
                Unit.KUS,
                new BigDecimal(1),
                "http://www.hej.sk/plienky-pampers-active-baby-dry-vel-3-midi-90ks/");

        HejEshopProductParser parser = new HejEshopProductParser();
        EshopProductInfo productInfo = parser.getProductInfo(parserInputData);
        System.out.println("Done");

    }



}
