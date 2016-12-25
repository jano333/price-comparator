package sk.hudak.pricecomparator.server.eshops.ng.tesco;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.task.AbstractPictureDownloaderTask;

import javax.inject.Named;

/**
 * Created by jan on 19. 11. 2016.
 */
@Named
public class TescoPictureDownloaderTask extends AbstractPictureDownloaderTask {



    @Override
    public EshopType getEshopType() {
        return EshopType.TESCO;
    }



    @Override
    public int getTaskPriority() {
        return 1;
    }
}
