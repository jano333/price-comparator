package sk.hudak.pricecomparator.server.core;

import org.apache.commons.lang3.StringUtils;
import sk.hudak.pricecomparator.middle.api.exeption.PriceComparatorException;

import javax.inject.Named;

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


    public void maxLength(String value, int maxLength, String errMsg) {
        //TODO koltola na maximalnu dlzku
    }
}
