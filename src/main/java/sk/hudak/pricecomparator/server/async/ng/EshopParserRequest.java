package sk.hudak.pricecomparator.server.async.ng;

import sk.hudak.pricecomparator.middle.canonical.Unit;

import java.math.BigDecimal;

/**
 * Created by jan on 9. 7. 2016.
 */
public class EshopParserRequest {

    // merna jednotka
    private Unit unit;

    /**
     * v pripade {@link Unit#KUS} je null
     * v pripade {@link Unit#LITER} je to kolko litrov ma 1 polozka v baliku (vodka 0.7l)
     * v pripade {@link Unit#KILOGRAM} je to kolko kil ma 1 polozka v baliku (cukor 1kg)
     * v pripade {@link Unit#METER} je to kolko metrom ma 1 polozka v baliku (toaletak 68m)
     */
    private BigDecimal countOfUnit;

    // pocet poloziek v baliku
    private int countOfItemInOnePackage;

    private String eshopProductPage;

    public Unit getUnit() {
        return unit;
    }

    public EshopParserRequest setUnit(Unit unit) {
        this.unit = unit;
        return this;
    }

    public BigDecimal getCountOfUnit() {
        return countOfUnit;
    }

    public EshopParserRequest setCountOfUnit(BigDecimal countOfUnit) {
        this.countOfUnit = countOfUnit;
        return this;
    }

    public int getCountOfItemInOnePackage() {
        return countOfItemInOnePackage;
    }

    public EshopParserRequest setCountOfItemInOnePackage(int countOfItemInOnePackage) {
        this.countOfItemInOnePackage = countOfItemInOnePackage;
        return this;
    }

    public String getEshopProductPage() {
        return eshopProductPage;
    }

    public EshopParserRequest setEshopProductPage(String eshopProductPage) {
        this.eshopProductPage = eshopProductPage;
        return this;
    }
}
