package sk.hudak.pricecomparator.server.async.impl;


import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.server.async.EshopSimpleTask;

import java.util.List;

/**
 * Hlavna implementacia pre kazdy eshop. Jedinovu ulohou je spustenie vsetkych subtaskov pre dany eshop v poradi v
 * akom boli popridavane.
 * <p/>
 * Trieda nie je public, aby sa nedali vytvarat instancie mimo TaskManagerImpl.
 * <p/>
 * Created by hudak on 10.05.2016.
 */
@Deprecated
class EshopMainTaskImpl implements Runnable {

    private EshopType type;
    private List<EshopSimpleTask> tasks;

    public EshopMainTaskImpl(EshopType type, List<EshopSimpleTask> tasks) {
        this.type = type;
        this.tasks = tasks;
    }

    @Override
    public void run() {
        LogUtil.log(">> start");
        // spustim tasky v poradi
        for (EshopSimpleTask task : tasks) {
            task.run();
        }
        // vsetko bolo upesne ukoncene
        LogUtil.log("<< end all ok");

//        try {
//            Thread.sleep(5 * 1000);
//
//        } catch (InterruptedException e) {
//            LogUtil.log("<< bol som preruseny v spani 1");
//            return;
//        }
//
//        LogUtil.log(" bezim");
//
//        try {
//            Thread.sleep(5 * 1000);
//
//        } catch (InterruptedException e) {
//            LogUtil.log("<< bol som preruseny v spani 2");
//            return;
//        }

    }
}
