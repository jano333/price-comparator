package sk.hudak.pricecomparator.server.async.ng.tesco;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.async.ng.EshopTaskCallbackNg;
import sk.hudak.pricecomparator.server.async.ng.EshopTaskNg;

/**
 * Created by jan on 3. 7. 2016.
 */
public class TescoProductPictureDownloaderTaskNg implements EshopTaskNg {

    private static Logger logger = LoggerFactory.getLogger(TescoProductPictureDownloaderTaskNg.class);

    @Override
    public EshopType getEshopType() {
        return EshopType.TESCO;
    }

    @Override
    public void run(PriceComparatorService service, EshopTaskCallbackNg callback) {
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
