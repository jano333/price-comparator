package sk;

import sk.hudak.pricecomparator.middle.api.canonical.ParserInputData;
import sk.hudak.pricecomparator.middle.api.canonical.Unit;
import sk.hudak.pricecomparator.server.core.AbstractEshopProductInfo;

import java.math.BigDecimal;

/**
 * Created by jan on 23. 11. 2015.
 */
public class PriceCalculatorTest {

    public static void main(String[] args) {
        // Lovela Sensitive 2 x 5l - tekutý prací prostriedok
//        ParserInputData inputData = new ParserInputData(2, Unit.LITER, new BigDecimal(5), "");

        //HiPP Babysanft Čistiace vlhčené obrúsky ULTRA SENSITIVE bez parfumu (4x52ks)
//        ParserInputData inputData = new ParserInputData(4, Unit.LITER, new BigDecimal(52), "");
        ParserInputData inputData = new ParserInputData(3, Unit.LITER, new BigDecimal(208), "");

        AbstractEshopProductInfo abstractEshopProductInfo = new AbstractEshopProductInfo(inputData) {

            @Override
            public BigDecimal getPriceForPackage() {
                return new BigDecimal(20);
            }
        };

        System.out.println("per unit " + abstractEshopProductInfo.getPriceForUnit());
    }

}
