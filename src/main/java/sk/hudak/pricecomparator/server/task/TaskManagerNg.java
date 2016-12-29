package sk.hudak.pricecomparator.server.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.hudak.pricecomparator.middle.canonical.EshopType;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

/**
 * Created by jan on 19. 11. 2016.
 */
@Named
public class TaskManagerNg {

    private static final Logger logger = LoggerFactory.getLogger(TaskManagerNg.class);

    @Inject
    private List<AbtractEshopTask> tasks;

    private Map<EshopType, List<AbtractEshopTask>> registredTask = new HashMap<>();

    @PostConstruct
    private void init() {
        // rozdelim podla typov eshopov
        for (AbtractEshopTask task : tasks) {
            if (registredTask.get(task.getEshopType()) == null) {
                registredTask.put(task.getEshopType(), new ArrayList<AbtractEshopTask>());
            }
            registredTask.get(task.getEshopType()).add(task);
        }

        // zosortujem podla priority
        for (EshopType eshopType : registredTask.keySet()) {
            Collections.sort(registredTask.get(eshopType), new Comparator<AbtractEshopTask>() {
                @Override
                public int compare(AbtractEshopTask o1, AbtractEshopTask o2) {
                    if (o1.getTaskPriority() > o2.getTaskPriority()) {
                        return 1;
                    }
                    if (o1.getTaskPriority() < o2.getTaskPriority()) {
                        return -1;
                    }
                    return 0;
                }
            });
        }

        //TODO nie tu !!!
        start();
    }

    public void start() {

        // custom ssl to ignore SSL check for specific domains
        // TODO odkomntovat ked zrusim stary manager
//        JefSslManager.getInstance().init();

        for (final EshopType eshopType : registredTask.keySet()) {
            logger.debug("starting tasks for eshop {}, pocet {}", eshopType, registredTask.get(eshopType).size());
            ExecutorService executorService = Executors.newSingleThreadExecutor(new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r, eshopType.name());
                    thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                        @Override
                        public void uncaughtException(Thread t, Throwable e) {
                            logger.error("error in thread " + t.getName(), e);
                        }
                    });
                    return thread;
                }
            });

            for (final AbtractEshopTask task : registredTask.get(eshopType)) {
                Future<?> future = executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        task.taskJob();
                    }
                });
                //TODO ulozit FUTURE?
            }
            executorService.shutdown();
        }


    }


}
