package sk.hudak.pricecomparator.server.tobedeleted;

import sk.hudak.pricecomparator.middle.canonical.EshopType;

import java.util.List;

/**
 * Created by hudak on 10.05.2016.
 */
@Deprecated
public interface AsyncEshopTaskManager {

    /**
     * Zaregistruje pre dany eshop <code>eshopType</code> task <code>task</code>, musi byt minimalne jeden task,
     * ten co stahuje cenu.
     *
     * @param eshopType typ eshop-u
     * @param task
     */
    void registerTask(EshopType eshopType, EshopSimpleTask task);


    /**
     * @param eshopType typ eshop-u
     * @param tasks
     */
    void registerTasks(EshopType eshopType, List<EshopSimpleTask> tasks);

    /**
     * @param eshopType
     * @return
     */
    boolean isTaskRegistred(EshopType eshopType);

    void startTask(EshopType eshopType);

    void startTasks();

    boolean isTaskRunning(EshopType eshopType);

    boolean isAnyTaskRunning();


    void stopTasks();

    void stopTask(EshopType eshopType);


//    /**
//     * Zaregistruje eshop task. Pre jeden typ eshop-u smie existovat len jeden task.
//     * V pripade ze pre dany <code>eshopType</code> uz je task zaregistovany, tak nerobi nic.
//     *
//     * @param eshopType
//     */
//    @Deprecated
//    void registerTask(EshopType eshopType);
//
//    /**
//     * Zaregistruje zoznam
//     *
//     * @param eshopTypes
//     */
//    @Deprecated
//    void registerTasks(List<EshopType> eshopTypes);


}
