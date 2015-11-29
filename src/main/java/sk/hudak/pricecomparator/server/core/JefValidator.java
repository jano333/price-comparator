package sk.hudak.pricecomparator.server.core;

import sk.hudak.pricecomparator.middle.api.exeption.PriceComparatorException;

import javax.inject.Named;

/**
 * Zakladny validator
 * <p/>
 * Created by jan on 5. 4. 2015.
 */
@Named
public class JefValidator {

    public void checkNotNull(Object object, String errorMsg) {
        if (object == null) {
            throw new PriceComparatorException(errorMsg);
        }
    }

    public void checkNotNullAndNotEmpty(String value, String errorMsg) {
        //TODO
//        checkNotNull(value, paramName);
//        if (value.isEmpty()) {
//            throw new ServerRuntimeException(paramName + " is empty");
//        }
    }
}
