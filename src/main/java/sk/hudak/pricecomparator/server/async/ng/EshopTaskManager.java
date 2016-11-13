package sk.hudak.pricecomparator.server.async.ng;

import sk.hudak.pricecomparator.middle.canonical.EshopType;

/**
 * Created by jan on 3. 7. 2016.
 */
@Deprecated
public interface EshopTaskManager {

    /**
     * zaregistruje dany task
     *
     * @param eshopTask
     */
    void registerEshopTask(EshopTask eshopTask);

    /**
     * zaregistruje dany task aj s callbackom
     *
     * @param eshopTask
     * @param callback
     */
    void registerEshopTaskWithCallback(EshopTask eshopTask, EshopTaskCallback callback);

    /**
     * spusti vsetky zaregistrovane tasky
     */
    void startAllTasks();

    /**
     * spusti iba tasky pre zaregistrovnae pre vstupny eshop
     *
     * @param eshopType
     */
    void startTask(EshopType eshopType);
}
