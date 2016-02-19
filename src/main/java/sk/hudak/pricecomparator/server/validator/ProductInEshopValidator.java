package sk.hudak.pricecomparator.server.validator;

import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.middle.canonical.Unit;
import sk.hudak.pricecomparator.middle.exeption.PriceComparatorException;

import javax.inject.Named;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.EnumSet;

/**
 * Created by hudak on 18.2.2016.
 */
@Named
public class ProductInEshopValidator {

    public void validateEshopType(String eshopType) throws PriceComparatorException {
        validateNotNullAndNotEmpty(eshopType, "eshopType");

        if (!EnumSet.allOf(EshopType.class).contains(eshopType.toUpperCase())) {
            throw new PriceComparatorException("Eshop type " + eshopType + " is unknown.");
        }
    }

    public void validateProductName(String productName) throws PriceComparatorException {
        validateNotNullAndNotEmpty(productName, "productName");
    }

    public void validateUnit(String unit) throws PriceComparatorException {
        validateNotNullAndNotEmpty(unit, "unit");

        if (!EnumSet.allOf(Unit.class).contains(unit.toUpperCase())) {
            throw new PriceComparatorException("Unit name " + unit + " is unknown.");
        }
    }

    public void validateCountOfUnit(String countOfUnit) throws PriceComparatorException {
        validateNotNullAndNotEmpty(countOfUnit, "countOfUnit");
        try {
            BigDecimal value = new BigDecimal(countOfUnit);
            //ak to nie je kladne cislo a vecsie ako nula
            if (value.signum() != 0) {
                throw new PriceComparatorException("Count of unit " + countOfUnit + " must be greater than zero.");
            }
        } catch (NumberFormatException e) {
            throw new PriceComparatorException("Error while converting countOfUnit " + countOfUnit + " to "
                    + BigDecimal.class.getSimpleName(), e);
        }
    }

    public void validateCountOfItems(String countOfItems) throws PriceComparatorException {
        validateNotNullAndNotEmpty(countOfItems, "countOfItems");
        try {
            Integer value = Integer.valueOf(countOfItems);
            if (value.intValue() < 1) {
                throw new PriceComparatorException("Count of items " + countOfItems + " must be greater than zero.");
            }
        } catch (NumberFormatException e) {
            throw new PriceComparatorException("Error while converting countOfItems " + countOfItems + " to "
                    + Integer.class.getSimpleName(), e);
        }
    }


    public void validateUrlOfProduct(String urlOfProduct) throws PriceComparatorException {
        validateNotNullAndNotEmpty(urlOfProduct, "urlOfProduct");

        try {
            new URL(urlOfProduct);
        } catch (MalformedURLException e) {
            throw new PriceComparatorException("Url " + urlOfProduct + " has invalid format.");
        }

    }

    public void validateNotNullAndNotEmpty(String value, String paramName) throws PriceComparatorException {
        if (value == null || value.trim().isEmpty()) {
            throw new PriceComparatorException("Input param '" + paramName + "' is null or empty.");
        }
    }
}
