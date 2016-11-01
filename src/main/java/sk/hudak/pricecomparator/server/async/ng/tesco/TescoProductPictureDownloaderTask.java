package sk.hudak.pricecomparator.server.async.ng.tesco;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.async.ng.EshopTask;
import sk.hudak.pricecomparator.server.async.ng.EshopTaskCallback;

/**
 * Created by jan on 3. 7. 2016.
 */
public class TescoProductPictureDownloaderTask implements EshopTask {

    private static Logger logger = LoggerFactory.getLogger(TescoProductPictureDownloaderTask.class);

    @Override
    public EshopType getEshopType() {
        return EshopType.TESCO;
    }

    @Override
    public void run(PriceComparatorService service, EshopTaskCallback callback) {
        logger.debug("starting task");
        //TODO
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.debug("finished task");
    }
}
