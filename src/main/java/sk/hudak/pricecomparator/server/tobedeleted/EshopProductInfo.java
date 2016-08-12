package sk.hudak.pricecomparator.server.tobedeleted;

import sk.hudak.pricecomparator.middle.canonical.ProductAction;
import sk.hudak.pricecomparator.middle.canonical.Unit;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jan on 13. 10. 2015.
 */
@Deprecated
public interface EshopProductInfo {

    /**
     * @return informacie o tom ci je dany produkt v akcii v danom eshope.
     */
    ProductAction getAction();

    /**
     * @return ak je produkt v akcii a je mozne zistit do kedy plati
     * dana akcia tak vrati datum, inak null
     */
    Date getActionValidTo();

    /**
     * Ak je v baleni 6ks piv, tak je to cena za vsetkych 6 ks.
     *
     * @return cena za cele balenie
     */
    BigDecimal getPriceForPackage();

    /**
     * Ak je v baleni 6ks piv, tak je to cena za jeden kus.
     *
     * @return cena za jeden kus v baleni
     */
    BigDecimal getPriceForOneItemInPackage();

    /**
     * Ak je to napr. pivo, tak je to cena za 1 liter.
     * Ak je to napr. toaleny papier, tak je to cena za 1 meter.
     * Ak je to napr. sol do kupela, tak je to cena za 1 kilogram.
     * Ak su to napr. plienky, tak je to cena za jednu plienku(tu je to to iste ako
     * {@link #getPriceForOneItemInPackage()}
     *
     * @return cena prepocitana za mernu jednotku {@link Unit}.
     */
    BigDecimal getPriceForUnit();

    /**
     * @return URL na stiahnutie obrazka produktu v danom eshope
     */
    String getProductImageUrl();

    /**
     * @return nazov produktu v eshope
     */
    String getProductNameInEhop();

}
