package sk.hudak.pricecomparator.server.eshops.ng.feedo;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.task.AbstractNewProductDownloader;

/**
 * Created by jan on 27. 12. 2016.
 */
public class FeedoNewProductDownloaderTask extends AbstractNewProductDownloader {


    @Override
    public EshopType getEshopType() {
        return null;
    }

    @Override
    public void taskJob() {

    }

    @Override
    public int getTaskPriority() {
        return 0;
    }
}
