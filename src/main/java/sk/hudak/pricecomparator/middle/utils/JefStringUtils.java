package sk.hudak.pricecomparator.middle.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by jan on 21. 2. 2016.
 */
public class JefStringUtils {

    public static String convertAndRound(BigDecimal value, int pocetDesatinych) {
        if (value == null) {
            return null;
        }
        BigDecimal newValue = value.setScale(pocetDesatinych, RoundingMode.HALF_UP);
        return String.valueOf(newValue);
    }
}
