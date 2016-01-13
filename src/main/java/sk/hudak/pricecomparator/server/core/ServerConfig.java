package sk.hudak.pricecomparator.server.core;

/**
 * Created by jan on 15. 11. 2015.
 */
public class ServerConfig {

    public static int getMinWaitingTimeBeforeDownloadNextPriceInSecond() {
        return 5;
    }

    public static int getMaxWaitingTimeBeforeDownloadNextPriceInSecond() {
        return 15;
    }


    /**
     * Definuje maxmalny pocet v hodinych, pre produkty ktore je potrebne aktualizovat.
     *
     * @return
     */
    public static int getMaxOldProductPriceForUpdateInOurs() {
        return 24;
    }


    /**
     * @return root adresar, kde su ukladane obrazky k produktom
     */
    public static String getImagesRootDirectory() {
        return "C:\\price-comparator\\images\\";
    }

    /**
     * @return pocet miest na kolko sa ma zaokruhlovat
     */
    public static int getRoundingScale() {
        return 4;
    }


}
