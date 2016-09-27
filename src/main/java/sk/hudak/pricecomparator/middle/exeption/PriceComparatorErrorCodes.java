package sk.hudak.pricecomparator.middle.exeption;

/**
 * Created by jan on 11. 6. 2016.
 */
public class PriceComparatorErrorCodes {
    /**
     * Typ eshopu je povinny paramter.
     */
    public static final String ERR_ESHOP_TYPE_IS_REQIRED = "err-eshop-type-is-reqired";

    /**
     * Eshop s nazvom {0} uz existuje.
     */
    public static final String ERR_ESHOP_NAME_ALLREADY_EXIST = "err-eshop-name-allready-exist";

    /**
     * Eshop s typom {0} uz existuje.
     */
    public static final String ERR_ESHOP_TYPE_ALLREADY_EXIST = "err-eshop-type-allready-exist";

    /**
     * "Produkt s URL {} uz existuje."
     */
    public static final String ERR_PRODUCT_WITH_URL_ALLREADY_EXIST = "err-product-with-url-allready-exist";
}
