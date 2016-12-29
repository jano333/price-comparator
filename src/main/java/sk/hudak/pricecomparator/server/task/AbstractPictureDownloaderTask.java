package sk.hudak.pricecomparator.server.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.task.picture.PictureDownloaderBean;
import sk.hudak.pricecomparator.server.to.ProductInEshopPictureDto;

import javax.inject.Inject;

/**
 * Created by jan on 19. 11. 2016.
 */
public abstract class AbstractPictureDownloaderTask extends AbtractEshopTask {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private PictureDownloaderBean downloader;

    // TODO dat tu InternalTxService
    @Inject
    private PriceComparatorService priceComparatorService;

    @Override
    public void taskJob() {
        logger.debug(">> job stated");
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
        String pathToSave = configNg.getImagesRootDirectory() + "pie_" + result.getProductInEshopId();
        boolean downloadOk = downloader.downloadPicture(result.getPictureUrl(), pathToSave);
        logger.debug("downloaded ok {}", downloadOk);

        priceComparatorService.markPictureOfProductInEshopAsDownloaded(result.getProductInEshopId());

        return true;
    }




}
