package sk.hudak.pricecomparator.server.core;

import javax.inject.Named;

/**
 * Created by hudak on 24.12.2016.
 */
@Named
public class ServerConfigNg {

    public int getMinWaitingTimeBeforeDownloadNextPriceInSecond() {
        return 5;
    }

    public int getMaxWaitingTimeBeforeDownloadNextPriceInSecond() {
        return 15;
    }


    /**
     * Definuje maxmalny pocet v hodinych, pre produkty ktore je potrebne aktualizovat.
     *
     * @return
     */
    public int getMaxOldProductPriceForUpdateInOurs() {
        return 24;
    }


    /**
     * @return root adresar, kde su ukladane obrazky k produktom
     */
    public String getImagesRootDirectory() {
        return "C:\\price-comparator\\images\\";
    }

    /**
     * @return pocet miest na kolko sa ma zaokruhlovat
     */
    public int getRoundingScale() {
        return 4;
    }

}
