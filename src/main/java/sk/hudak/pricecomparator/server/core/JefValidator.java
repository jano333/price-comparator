package sk.hudak.pricecomparator.server.core;

import org.apache.commons.lang3.StringUtils;
import sk.hudak.pricecomparator.middle.exeption.PriceComparatorException;

import javax.inject.Named;
import java.math.BigDecimal;

/**
 * Zakladny val
 * <p/>
 * Created by jan on 5. 4. 2015.
 */
@Named
public class JefValidator {

    public void notNull(Object object, String errorMsg) {
        if (object == null) {
            throw new PriceComparatorException(errorMsg);
        }
    }

    public void notNullAndNotEmpty(String value, String errorMsg) {
        if (StringUtils.isBlank(value)) {
            throw new PriceComparatorException(errorMsg);
        }
    }

    public void maxLength255(String value, String errMsg) {
        maxLength(value, 255, errMsg);
    }

    public void maxLength(String value, int maxLength, String errMsg) {
        if (StringUtils.isBlank(value)) {
            return;
        }
        if (value.length() > maxLength) {
            throw new PriceComparatorException(errMsg);
        }
    }

    public void greaterThanZero(BigDecimal value, String errMsg) {
        if (value == null) {
            return;
        }
        int i = value.compareTo(BigDecimal.ZERO);
        if (i != 1) {
            throw new PriceComparatorException(errMsg);
        }
    }

    public void greaterThanZero(Integer value, String errMsg) {
        if (value == null) {
            return;
        }
        if (value.intValue() < 1) {
            throw new PriceComparatorException(errMsg);
        }
    }
}
