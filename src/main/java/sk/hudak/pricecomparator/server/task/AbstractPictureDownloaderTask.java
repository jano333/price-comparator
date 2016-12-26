package sk.hudak.pricecomparator.server.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.core.ServerConfigNg;
import sk.hudak.pricecomparator.server.task.picture.PictureDownloaderBean;
import sk.hudak.pricecomparator.server.to.ProductInEshopPictureDto;

import javax.inject.Inject;
import java.util.Random;

/**
 * Created by jan on 19. 11. 2016.
 */
public abstract class AbstractPictureDownloaderTask extends AbtractEshopTask {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private PictureDownloaderBean downloader;

    @Inject
    private ServerConfigNg configNg;

    // TODO dat tu InternalTxService

    @Inject
    private PriceComparatorService priceComparatorService;

    @Override
    public void taskJob() {
        logger.debug(">> job stated");
        //TODO
        if (true) {
            logger.debug("<< job finished");
            return;
        }
        boolean isNext = downloadPicture();
        while (isNext) {
            sleepFor();
            isNext = downloadPicture();
        }
        logger.debug("<< job finished");
    }

    private boolean downloadPicture() {
        ProductInEshopPictureDto result = priceComparatorService.findUrlOfPictureForDownload(getEshopType());
        if (result == null) {
            logger.debug("nic nenaslo -> vsetky obrazky stiahnte");
            return false;
        }

        String pathToSave = null;
        boolean downloadOk = downloader.downloadPicture(result.getPictureUrl(), pathToSave);
        logger.debug("downloaded ok {}", downloadOk);

        priceComparatorService.markPictureOfProductInEshopAsDownloaded(result.getProductInEshopId());

        return true;
    }

    private void sleepFor() {
        int minSecond = configNg.getMinWaitingTimeBeforeDownloadNextPriceInSecond();
        int maxSecond = configNg.getMaxWaitingTimeBeforeDownloadNextPriceInSecond();

        int result = new Random().nextInt((maxSecond - minSecond) + 1) + minSecond;

        try {
            logger.debug("zacinam cakat " + result + " sekund");
            Thread.currentThread().sleep(result * 1000);
            logger.debug("skoncil som cakat");

        } catch (InterruptedException e) {
            logger.error("sleeping was inteapted", e);
        }
    }


}
