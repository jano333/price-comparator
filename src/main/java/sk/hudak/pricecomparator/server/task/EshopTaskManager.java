package sk.hudak.pricecomparator.server.task;

import sk.hudak.pricecomparator.client.swing.components.TaskManager;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by jan on 5. 4. 2016.
 */
@Named
public class EshopTaskManager  {

    @Inject
    private PriceComparatorService service;

    private TaskManager taskManager;

    @PostConstruct
    public void init(){
        System.out.println("init call");

        taskManager = new TaskManager();
        taskManager.initTaks(service);
        taskManager.startDownloading();
    }

    @PreDestroy
    public void destroy(){
        System.out.println("Destroy call");

        taskManager.stopDownloading();
    }
}
