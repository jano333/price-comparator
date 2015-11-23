package sk.hudak.pricecomparator.server.core;

import sk.hudak.pricecomparator.middle.api.canonical.ParserInputData;
import sk.hudak.pricecomparator.middle.api.exeption.PriceComparatorException;
import sk.hudak.pricecomparator.middle.api.model.EshopProductInfo;
import sk.hudak.pricecomparator.middle.api.model.ProductAction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

/**
 * Created by jan on 14. 10. 2015.
 */
public abstract class AbstractEshopProductInfo implements EshopProductInfo {

    private ParserInputData parserInputData;

    public AbstractEshopProductInfo(ParserInputData parserInputData) {
        this.parserInputData = parserInputData;
    }

    @Override
    public ProductAction getAction() {
        return ProductAction.UNKNOWN;
    }

    @Override
    public Date getActionValidTo() {
        return null;
    }

    @Override
    public String getProductImageUrl() {
        return null;
    }

    @Override
    public BigDecimal getPriceForOneItemInPackage() {
        return calculatePriceForOneItemInPackage(getPriceForPackage());
    }

    @Override
    public BigDecimal getPriceForUnit() {
        switch (parserInputData.getUnit()) {
            case KUS:
                return calculatePriceForUnit_KUS();
            case LITER:
                return calculatePriceForUnit_LITER();
            case METER:
                return calculatePriceForUnit_METER();
            case KILOGRAM:
                return calculatePriceForUnit_KILOGRAM();
            case DAVKA:
                return calculatePriceForUnit_DAVKA();
            default:
                throw new PriceComparatorException("Not defined type " + parserInputData.getUnit());
        }
    }

    /**
     * balenie (4x52ks)
     * @return
     */
    private BigDecimal calculatePriceForUnit_KUS() {
        // napr.: 52
        BigDecimal countOfUnit = parserInputData.getCountOfUnit();
        // cena za jedno balenie(52 kusov)
        BigDecimal priceForOneItemInPackage = getPriceForOneItemInPackage();
        // kolko stoji 1 kus?
        return priceForOneItemInPackage.divide(countOfUnit, ServerConfig.getRoundingScale(), RoundingMode.HALF_UP);
    }

    private BigDecimal calculatePriceForUnit_LITER() {
        // napr.: 0.7 litra
        BigDecimal countOfUnit = parserInputData.getCountOfUnit();
        // to znamena ze 0.7 litra stoji 'priceForOneItemInPackage'
        BigDecimal priceForOneItemInPackage = getPriceForOneItemInPackage();
        // kolko stoje 1 liter?
        return priceForOneItemInPackage.divide(countOfUnit, ServerConfig.getRoundingScale(), RoundingMode.HALF_UP);
    }

    private BigDecimal calculatePriceForUnit_METER() {
        // napr.: 68 metrov
        BigDecimal countOfUnit = parserInputData.getCountOfUnit();
        // to znamena ze 68 metrov stoji 'priceForOneItemInPackage'
        BigDecimal priceForOneItemInPackage = getPriceForOneItemInPackage();
        // kolko stoje 1 meter ?
        return priceForOneItemInPackage.divide(countOfUnit, ServerConfig.getRoundingScale(), RoundingMode.HALF_UP);
    }

    private BigDecimal calculatePriceForUnit_KILOGRAM() {
        // napr.: 0.75 kg
        BigDecimal countOfUnit = parserInputData.getCountOfUnit();
        // to znamena ze 0.75kg stoji 'priceForOneItemInPackage'
        BigDecimal priceForOneItemInPackage = getPriceForOneItemInPackage();
        // kolko stoje 1 kilo ?
        return priceForOneItemInPackage.divide(countOfUnit, ServerConfig.getRoundingScale(), RoundingMode.HALF_UP);
    }

    private BigDecimal calculatePriceForUnit_DAVKA() {
        BigDecimal countOfUnit = parserInputData.getCountOfUnit();
        BigDecimal priceForOneItemInPackage = getPriceForOneItemInPackage();
        return priceForOneItemInPackage.divide(countOfUnit, ServerConfig.getRoundingScale(), RoundingMode.HALF_UP);
    }

    /**
     * Cenu za balenie podelime poctom kusov v baleni.
     *
     * @param priceForPackage
     * @return
     */
    protected BigDecimal calculatePriceForOneItemInPackage(BigDecimal priceForPackage) {
        //TODO pocit iba ak je rozdny od jedna, lebo inak to je zbytocne teda je to rovnake ako cena za balenie
        BigDecimal countOfItemInPackage = new BigDecimal(parserInputData.getCountOfItemInOnePackage());
        BigDecimal result = priceForPackage.divide(countOfItemInPackage, ServerConfig.getRoundingScale(), RoundingMode.HALF_UP);
        return result;
    }


}
