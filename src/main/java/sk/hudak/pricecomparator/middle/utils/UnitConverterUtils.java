package sk.hudak.pricecomparator.middle.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * Created by hudak on 29.2.2016.
 */
public class UnitConverterUtils {

    private static final BigDecimal ONE_THOUSAND = BigDecimal.valueOf(1000);

    public static BigDecimal convertToKilogram(String pocetGramov) {
        if (StringUtils.isBlank(pocetGramov)) {
            return null;
        }
        return new BigDecimal(pocetGramov).divide(ONE_THOUSAND);
    }

    public static BigDecimal convertToLiter(String pocetMilitrov) {
        if (StringUtils.isBlank(pocetMilitrov)) {
            return null;
        }
        return new BigDecimal(pocetMilitrov).divide(ONE_THOUSAND);
    }
}
