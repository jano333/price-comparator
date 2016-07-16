package sk.hudak.pricecomparator.server.async.ng;

import sk.hudak.pricecomparator.middle.EshopType;

/**
 * Created by jan on 3. 7. 2016.
 */
public interface EshopTaskManagerNg {

    /**
     * zaregistruje dany task
     *
     * @param eshopTaskNg
     */
    void registerEshopTask(EshopTaskNg eshopTaskNg);

    /**
     * zaregistruje dany task aj s callbackom
     *
     * @param eshopTaskNg
     * @param callback
     */
    void registerEshopTaskWithCallback(EshopTaskNg eshopTaskNg, EshopTaskCallbackNg callback);

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
