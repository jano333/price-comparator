package sk.hudak.pricecomparator.server.core;

/**
 * Created by jan on 15. 11. 2015.
 */
public class ServerConfig {

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
        return 3;
    }
}
