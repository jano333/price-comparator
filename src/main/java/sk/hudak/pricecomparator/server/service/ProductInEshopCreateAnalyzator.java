package sk.hudak.pricecomparator.server.service;

import org.apache.commons.lang3.StringUtils;
import sk.hudak.pricecomparator.middle.canonical.Unit;
import sk.hudak.pricecomparator.middle.exeption.PriceComparatorException;
import sk.hudak.pricecomparator.middle.to.ProductAnalyzatorDto;
import sk.hudak.pricecomparator.middle.utils.UnitConverterUtils;

import javax.inject.Named;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hudak on 25.2.2016.
 */
@Named
public class ProductInEshopCreateAnalyzator {

    public static final int DEFAULT_COUNT_OF_ITEM_IN_PACKAGE = 1;

    public static void main(String[] args) {
//        String productName = "Danone Fantasia 15 Sladený jogurt a jahody 22 g";
//        String productName = "Danone Fantasia Sladený jogurt a jahody 122 kg";
//        String productName = "NUTRILON 2 ProNutra (350g) - dojčenské mlieko";
//        String productName = "Vinea Biela nealkoholický hroznový nápoj sýtený 1,5 l";
//        String productName = "Vinea Biela nealkoholický hroznový nápoj sýtený 15 l";
//        String productName = "Vinea Biela nealkoholický hroznový nápoj sýtený 15 l";
//        String productName = "Vinea Biela nealkoholický hroznový nápoj sýtený 1.5 l";
//        String productName = "Pilsner Urquell Pivo svetlý ležiak 500 ml";
//        String productName = "Hubert De Luxe Akostné aromatické šumivé víno biele sladké doux 0,75 l";
//        String productName = "Tesco Čerstvé vajcia L 10 ks";
//        String productName = "Karička Klasik MAXI 16 ks 250 g";
        String productName = "6x NUTRILON 2 ProNutra (800g) - dojčenské mlieko";


        System.out.println(new ProductInEshopCreateAnalyzator().analyzeFromName(productName));
    }

    private static Set<String> gramUnitRegex = new HashSet<>(1);
    private static Set<String> kilogramUnitRegex = new HashSet<>(2);
    private static Set<String> mililiterUnitRegex = new HashSet<>(1);
    private static Set<String> literUnitRegex = new HashSet<>(2);
    private static Set<String> kusUnitRegex = new HashSet<>(1);

    private static Set<String> countOfItemRegex = new HashSet<>(1);


    static {
        gramUnitRegex.add("\\d+ ?g");   //'22 g' '22g'

        kilogramUnitRegex.add("\\d+,?\\d* ?kg");
        kilogramUnitRegex.add("\\d+.?\\d* ?kg");

        mililiterUnitRegex.add("\\d+ ?ml");

        literUnitRegex.add("\\d+,?\\d* ?l");
        literUnitRegex.add("\\d+.?\\d* ?l");

        kusUnitRegex.add("\\d+ ?ks");

        countOfItemRegex.add("\\d+");
    }

    public ProductAnalyzatorDto analyzeFromName(String productName) {
        UnitDataResult unitDataResult = analyzeUnitAndCountOfUnit(productName);
        Unit unit = null;
        BigDecimal countOfUnit = null;
        if (unitDataResult != null) {
            unit = convertToUnit(unitDataResult.unitInternal);
            countOfUnit = convertToCountOfUnit(unitDataResult);
        }
        return new ProductAnalyzatorDto()
                .setUnit(unit)
                .setCountOfUnit(countOfUnit)
                .setCountOfItemInPackage(analyzePocetJednotiek(productName, unitDataResult));
    }

    private int analyzePocetJednotiek(String productName, UnitDataResult unitDataResult) {
        if (unitDataResult == null) {
            return DEFAULT_COUNT_OF_ITEM_IN_PACKAGE;
        }

        // TODO doplnit nech prioritne berie ak je naprcislo a napr. 6x alebo 4 ks ale nesmie to byt nieco
        // co uz je v count of unit
        // "6x NUTRILON 2 ProNutra (800g) - dojčenské mlieko"
        // "Karička klasik 8 ks 125 g"

        Set<String> numberValues = new HashSet<>();
        for (String countOfItemRegex : ProductInEshopCreateAnalyzator.countOfItemRegex) {
            Matcher matcher = Pattern.compile(countOfItemRegex).matcher(productName);
            while (matcher.find()) {
                numberValues.add(matcher.group());
            }
        }
        if (numberValues.isEmpty()) {
            return DEFAULT_COUNT_OF_ITEM_IN_PACKAGE;
        }
        String countOfUnit = unitDataResult.countOfUnit;
        if (countOfUnit != null) {
            numberValues.remove(countOfUnit);
        }
        if (numberValues.isEmpty()) {
            return DEFAULT_COUNT_OF_ITEM_IN_PACKAGE;
        }
        return Integer.parseInt(numberValues.iterator().next());
    }

    private BigDecimal convertToCountOfUnit(UnitDataResult unitDataResult) {
        if (unitDataResult.unitInternal == null || StringUtils.isBlank(unitDataResult.countOfUnit)) {
            return null;
        }
        switch (unitDataResult.unitInternal) {
            case GRAM:
                return UnitConverterUtils.convertToKilogram(unitDataResult.countOfUnit);

            case MILILITER:
                return UnitConverterUtils.convertToLiter(unitDataResult.countOfUnit);

            case KUS:
            case LITER:
            case KILOGRAM:
                return new BigDecimal(unitDataResult.countOfUnit);

            default:
                throw new PriceComparatorException("neznamy typ");
        }
    }


    private UnitDataResult analyzeUnitAndCountOfUnit(String productName) {

        // GRAM
        for (String gramRegex : gramUnitRegex) {
            Matcher matcher = Pattern.compile(gramRegex).matcher(productName);
            if (matcher.find()) {

                StringBuilder sb = new StringBuilder(matcher.group());
                sb.deleteCharAt(sb.length() - 1);
                String countOfUnit = sb.toString().trim();

                UnitDataResult unitDataResult = new UnitDataResult();
                unitDataResult.unitInternal = UnitInternal.GRAM;
                unitDataResult.countOfUnit = countOfUnit;
                return unitDataResult;
            }
        }

        // KILOGRAM
        for (String kilogramUnit : kilogramUnitRegex) {
            Matcher matcher = Pattern.compile(kilogramUnit).matcher(productName);
            if (matcher.find()) {

                StringBuilder sb = new StringBuilder(matcher.group());
                sb.deleteCharAt(sb.length() - 1);
                sb.deleteCharAt(sb.length() - 1);
                String countOfUnit = sb.toString().trim();

                UnitDataResult unitDataResult = new UnitDataResult();
                unitDataResult.unitInternal = UnitInternal.KILOGRAM;
                unitDataResult.countOfUnit = countOfUnit;
                return unitDataResult;
            }
        }

        // MILILITER
        for (String mililiterUnit : mililiterUnitRegex) {
            Matcher matcher = Pattern.compile(mililiterUnit).matcher(productName);
            if (matcher.find()) {

                StringBuilder sb = new StringBuilder(matcher.group());
                sb.deleteCharAt(sb.length() - 1);
                sb.deleteCharAt(sb.length() - 1);
                String countOfUnit = sb.toString().trim();

                UnitDataResult unitDataResult = new UnitDataResult();
                unitDataResult.unitInternal = UnitInternal.MILILITER;
                unitDataResult.countOfUnit = countOfUnit;
                return unitDataResult;
            }
        }

        // LITER
        for (String literUnit : literUnitRegex) {
            Matcher matcher = Pattern.compile(literUnit).matcher(productName);
            if (matcher.find()) {

                StringBuilder sb = new StringBuilder(matcher.group());
                sb.deleteCharAt(sb.length() - 1);
                String countOfUnit = sb.toString().trim();

                UnitDataResult unitDataResult = new UnitDataResult();
                unitDataResult.unitInternal = UnitInternal.LITER;
                unitDataResult.countOfUnit = countOfUnit;
                return unitDataResult;
            }
        }

        // KUS
        for (String ksUnit : kusUnitRegex) {
            Matcher matcher = Pattern.compile(ksUnit).matcher(productName);
            if (matcher.find()) {

                StringBuilder sb = new StringBuilder(matcher.group());
                sb.deleteCharAt(sb.length() - 1);
                sb.deleteCharAt(sb.length() - 1);
                String countOfUnit = sb.toString().trim();

                UnitDataResult unitDataResult = new UnitDataResult();
                unitDataResult.unitInternal = UnitInternal.KUS;
                unitDataResult.countOfUnit = countOfUnit;
                return unitDataResult;
            }
        }
        return null;
    }


    private enum UnitInternal {


        GRAM,
        KILOGRAM,

        MILILITER,
        LITER,

        KUS,

        //TODO dalsie

    }

    private class UnitDataResult {
        UnitInternal unitInternal;
        String countOfUnit;

        @Override
        public String toString() {
            return "UnitDataResult{" +
                    "unitInternal=" + unitInternal +
                    ", countOfUnit='" + countOfUnit + '\'' +
                    '}';
        }
    }


    private Unit convertToUnit(UnitInternal unitInternal) {
        if (unitInternal == null) {
            return null;
        }
        switch (unitInternal) {
            case GRAM:
            case KILOGRAM:
                return Unit.KILOGRAM;

            case MILILITER:
            case LITER:
                return Unit.LITER;

            case KUS:
                return Unit.KUS;
            default:
                throw new PriceComparatorException("Not supported " + UnitInternal.class.getSimpleName() + " for " + unitInternal);
        }

    }


}
