package sk.hudak.pricecomparator.server.eshops.ng.tesco;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.task.AbstractPictureDownloaderTask;

import javax.inject.Named;

/**
 * Created by jan on 19. 11. 2016.
 */
@Named
public class TescoPictureDownloaderTask extends AbstractPictureDownloaderTask {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public EshopType getEshopType() {
        return EshopType.TESCO;
    }

    @Override
    public void taskJob() {
        logger.debug(">> job stated");
        // TODO v DB musi byt zaznam ci exituje obrazok k produktu alebo nie !!!
        //1. vytiahnut
        // toto
        logger.debug("<< job finished");
    }

    @Override
    public int getTaskPriority() {
        return 1;
    }
}
