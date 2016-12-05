package sk.hudak.pricecomparator.server.task;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;

import javax.inject.Inject;

/**
 * Created by jan on 19. 11. 2016.
 */
public abstract class AbtractEshopTask {

    //TODO  nazvat to na InternalTxTesrvice??
    @Inject
    protected PriceComparatorService service;

    public abstract EshopType getEshopType();

    public abstract void taskJob();

    /**
     * 1 je najvyssia priorita
     *
     * @return
     */
    public abstract int getTaskPriority();


}
