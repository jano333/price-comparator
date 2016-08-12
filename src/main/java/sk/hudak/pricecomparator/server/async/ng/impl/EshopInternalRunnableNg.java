package sk.hudak.pricecomparator.server.async.ng.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.async.ng.EshopTaskCallbackNg;
import sk.hudak.pricecomparator.server.async.ng.EshopTaskNg;

import java.util.List;

/**
 * Created by jan on 9. 7. 2016.
 */
public class EshopInternalRunnableNg implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(EshopInternalRunnableNg.class);

    private PriceComparatorService service;
    private EshopType eshopType;
    private List<EshopTaskManagerImpNg.TaskWithCallback> taskWithCallbacks;

    public EshopInternalRunnableNg(PriceComparatorService service, EshopType eshopType, List<EshopTaskManagerImpNg.TaskWithCallback> taskWithCallbacks) {
        this.service = service;
        this.eshopType = eshopType;
        this.taskWithCallbacks = taskWithCallbacks;
    }

    @Override
    public void run() {
        logger.debug("starting tasks({}) for eshop {}", taskWithCallbacks.size(), eshopType);

        for (EshopTaskManagerImpNg.TaskWithCallback taskWithCallback : taskWithCallbacks) {
            EshopTaskCallbackNg callback = taskWithCallback.getCallback();
            EshopTaskNg task = taskWithCallback.getTask();

            task.run(service, callback);

            //TODO pred dalsim pockat urceny cas...
        }

    }
}
