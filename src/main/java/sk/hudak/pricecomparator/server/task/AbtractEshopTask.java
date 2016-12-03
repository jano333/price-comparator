package sk.hudak.pricecomparator.server.task;

import sk.hudak.pricecomparator.middle.canonical.EshopType;

/**
 * Created by jan on 19. 11. 2016.
 */
public abstract class AbtractEshopTask {

    public abstract EshopType getEshopType();

    public abstract void taskJob();


}
