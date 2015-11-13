package sk.hudak.pricecomparator.middle.api.model;

import sk.hudak.pricecomparator.middle.api.canonical.Unit;

import java.math.BigDecimal;

/**
 * Created by jan on 13. 10. 2015.
 */
public interface Product {

    /**
     * @return jednoznacnu identifikator produktu
     */
    Long getId();

    /**
     * @return nazov produktu
     */
    String getName();

    /**
     * @return pocet poloziek v baliku
     */
    long getCountOfItemInOnePackage(); //TODO zmenit na int

    /**
     * @return merna jednotka
     */
    Unit getUnit();

    /**
     * v pripade {@link Unit#KUS} je null
     * v pripade {@link Unit#LITER} je to kolko litrov ma 1 polozka v baliku (vodka 0.7l)
     * v pripade {@link Unit#KILOGRAM} je to kolko kil ma 1 polozka v baliku (cukor 1kg)
     * v pripade {@link Unit#METER} je to kolko metrom ma 1 polozka v baliku (toaletak 68m)
     *
     * @return
     */
    BigDecimal getCountOfUnit();

    Category getCategory();

}
