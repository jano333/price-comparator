package sk.hudak.pricecomparator.server.tobedeleted;

import sk.hudak.pricecomparator.middle.canonical.Unit;

import java.math.BigDecimal;

/**
 * Created by jan on 6. 11. 2015.
 */
@Deprecated
public class ParserInputData {

    // pocet poloziek v baliku
    private int countOfItemInOnePackage;
    // merna jednotka
    private Unit unit;
    /**
     * v pripade {@link Unit#KUS} je null
     * v pripade {@link Unit#LITER} je to kolko litrov ma 1 polozka v baliku (vodka 0.7l)
     * v pripade {@link Unit#KILOGRAM} je to kolko kil ma 1 polozka v baliku (cukor 1kg)
     * v pripade {@link Unit#METER} je to kolko metrom ma 1 polozka v baliku (toaletak 68m)
     */
    private BigDecimal countOfUnit;

    private String eshopProductPage;

    public ParserInputData(int countOfItemInOnePackage, Unit unit, BigDecimal countOfUnit, String eshopProductPage) {
        this.countOfItemInOnePackage = countOfItemInOnePackage;
        this.unit = unit;
        this.countOfUnit = countOfUnit;
        this.eshopProductPage = eshopProductPage;
    }

    public int getCountOfItemInOnePackage() {
        return countOfItemInOnePackage;
    }

    public Unit getUnit() {
        return unit;
    }

    public BigDecimal getCountOfUnit() {
        return countOfUnit;
    }

    public String getEshopProductPage() {
        return eshopProductPage;
    }
}
