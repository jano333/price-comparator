package sk.hudak.pricecomparator.server.task.manager;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.task.AbtractEshopTask;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jan on 19. 11. 2016.
 */
@Named
public class TaskManager {

    @Inject
    private List<AbtractEshopTask> tasks;

    private Map<EshopType, List<AbtractEshopTask>> registredTask = new HashMap<>();

    @PostConstruct
    private void init() {
        for (AbtractEshopTask task : tasks) {
            if (registredTask.get(task.getEshopType()) == null) {
                registredTask.put(task.getEshopType(), new ArrayList<AbtractEshopTask>());
            }
            registredTask.get(task.getEshopType()).add(task);
        }


    }


}
