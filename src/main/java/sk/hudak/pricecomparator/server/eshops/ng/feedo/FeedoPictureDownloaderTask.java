package sk.hudak.pricecomparator.server.eshops.ng.feedo;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.task.AbstractPictureDownloaderTask;

import javax.inject.Named;

/**
 * Created by jan on 26. 12. 2016.
 */
@Named
public class FeedoPictureDownloaderTask extends AbstractPictureDownloaderTask {

    @Override
    public EshopType getEshopType() {
        return EshopType.FEEDO;
    }

    @Override
    public int getTaskPriority() {
        return 1;
    }
}
