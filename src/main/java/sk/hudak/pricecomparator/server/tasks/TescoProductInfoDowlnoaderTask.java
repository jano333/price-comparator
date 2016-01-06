package sk.hudak.pricecomparator.server.tasks;

/**
 * Created by jan on 4. 1. 2016.
 */
public class TescoProductInfoDowlnoaderTask extends EshopProductInfoDownloaderTask {

    @Override
    public DownloaderEshopType getEshopType() {
        return DownloaderEshopType.TESCO;
    }
}
