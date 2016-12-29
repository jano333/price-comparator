package sk.hudak.pricecomparator.server.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.core.ServerConfigNg;

import javax.inject.Inject;
import java.util.Random;

/**
 * Created by jan on 19. 11. 2016.
 */
public abstract class AbtractEshopTask {

    private Logger logger = LoggerFactory.getLogger(getClass());

    //TODO  nazvat to na InternalTxTesrvice??
    @Inject
    protected PriceComparatorService service;

    @Inject
    protected ServerConfigNg configNg;


    public abstract EshopType getEshopType();

    public abstract void taskJob();

    /**
     * 0 je najvyssia priorita
     *
     * @return
     */
    public abstract int getTaskPriority();

    protected void sleepFor() {
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
