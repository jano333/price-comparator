package sk.hudak.pricecomparator.middle.canonical;

/**
 * Definuje typ mernej jednotky.
 * <p>
 * Created by jan on 13. 10. 2015.
 */
public enum Unit {

    /**
     * napr. plienky
     */
    KUS("kus"),

    /**
     * napr.: sol do sol do kupela - 0,5kg
     */
    KILOGRAM("kg"),

    /**
     * napr.: toaletny papier - 68m
     */
    METER("m"),

    /**
     * napr.: alkohol - 0,7l
     */
    LITER("l"),

    //pozri http://potravinydomov.itesco.sk/sk-SK/ProductDetail/ProductDetail/2002015116798
    DAVKA("davka"),;

    private String jednotka;

    Unit(String jednotka) {
        this.jednotka = jednotka;
    }

    public String getJednotka() {
        return jednotka;
    }
}
