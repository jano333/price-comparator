package sk.hudak.pricecomparator.server.async.ng.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.async.ng.EshopTask;
import sk.hudak.pricecomparator.server.async.ng.EshopTaskCallback;

import java.util.List;

/**
 * Created by jan on 9. 7. 2016.
 */
public class EshopInternalRunnable implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(EshopInternalRunnable.class);

    private PriceComparatorService service;
    private EshopType eshopType;
    private List<EshopTaskManagerImp.TaskWithCallback> taskWithCallbacks;

    public EshopInternalRunnable(PriceComparatorService service, EshopType eshopType, List<EshopTaskManagerImp.TaskWithCallback> taskWithCallbacks) {
        this.service = service;
        this.eshopType = eshopType;
        this.taskWithCallbacks = taskWithCallbacks;
    }

    @Override
    public void run() {
        logger.debug("starting tasks({}) for eshop {}", taskWithCallbacks.size(), eshopType);

        for (EshopTaskManagerImp.TaskWithCallback taskWithCallback : taskWithCallbacks) {
            EshopTaskCallback callback = taskWithCallback.getCallback();
            EshopTask task = taskWithCallback.getTask();

            task.run(service, callback);

            //TODO pred dalsim pockat urceny cas...
        }

    }
}
