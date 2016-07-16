package sk.hudak.pricecomparator.server.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.server.async.impl.TaskManagerImpl;

/**
 * Created by hudak on 10.05.2016.
 */
@Deprecated
public class Starter {

    private static final Logger logger = LoggerFactory.getLogger(Starter.class);


    public static void main(String[] args) {

        logger.debug(">> start");
        AsyncEshopTaskManager taskManager = new TaskManagerImpl();

        // registrujem eshop-y
        taskManager.registerTask(EshopType.TESCO, new EshopSimpleTask() {
            @Override
            public void run() {
                System.out.println("Tesco... 1");
                try {
                    Thread.sleep(25 * 1000);
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                    System.out.println("!!! Tesco... 2");
                    return;
                }

                System.out.println("Tesco... 3");
            }
        });
        taskManager.registerTask(EshopType.METRO, new EshopSimpleTask() {
            @Override
            public void run() {
                System.out.println("Metro... ");
            }
        });

        logger.debug("tasky zaregistrovane");

        // spustim
        taskManager.startTasks();
        logger.debug("tasky spustene");

        int s = 8;
        logger.debug("zacinam cakat " + s + " sekundy");
        sleepFor(s);
        logger.debug("skoncil som cakat " + s + " sekundy");

        if (taskManager.isAnyTaskRunning()) {

            logger.debug("niektore tasky este bezia -> posielam request na stopnutie vsetkych");
            taskManager.stopTasks();
        } else {
            logger.debug("ziadne tasky nebezia");

        }
        logger.debug("<< koniec");
    }

    private static void sleepFor(int second) {
        try {
            Thread.sleep(second * 1000);

        } catch (InterruptedException e) {
            //ignore
            e.printStackTrace();
        }
    }


}
