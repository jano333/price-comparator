package sk.hudak.pricecomparator.client.swing.components;

import sk.hudak.pricecomparator.client.ServiceLocator;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.eshops.alza.AlzaProductInfoDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.andrea.AndreaEshopProductInfoDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.feedo.FeedoProductInfoDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.tesco.TescoProductInfoDownloaderTask;
import sk.hudak.pricecomparator.server.tasks.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jan on 14. 1. 2016.
 */
public class TaskManager2 {
    private PriceComparatorService service;
    private List<MyThread> taskList;

    public void initTaks() {
        service = ServiceLocator.getService();

        taskList = new ArrayList<>();
        taskList.add(new MyThread(new AlzaProductInfoDownloaderTask(service)));
        taskList.add(new MyThread(new AndreaEshopProductInfoDownloaderTask(service)));
        taskList.add(new MyThread(new BambinoProductInfoDownloaderTask(service)));
        taskList.add(new MyThread(new DrMaxProductInfoDownloaderTask(service)));
        taskList.add(new MyThread(new FeedoProductInfoDownloaderTask(service)));
        taskList.add(new MyThread(new HejProductInfoDownloaderTask(service)));
        taskList.add(new MyThread(new HravoZdravoProductInfoDownloaderTask(service)));
        taskList.add(new MyThread(new InternetovaLekarenProductInfoDownloaderTask(service)));
        taskList.add(new MyThread(new K24ProductInfoDownloaderTask(service)));
        taskList.add(new MyThread(new MallProductInfoDownloaderTask(service)));
        taskList.add(new MyThread(new MetroProductInfoDownloaderTask(service)));
        taskList.add(new MyThread(new PerinbabaProductInfoDownloaderTask(service)));
        taskList.add(new MyThread(new PilulkaProductInfoDownloaderTask(service)));
        taskList.add(new MyThread(new SemistorProductInfoDownloaderTask(service)));
        taskList.add(new MyThread(new TescoProductInfoDownloaderTask(service)));
    }

    public void startDownloading() {
        for (MyThread thread : taskList) {
            thread.start();
        }
    }


    public void stopDownloading() {
        for (MyThread thread : taskList) {
            thread.stopTask();
        }
    }

    private class MyThread extends Thread {
        private EshopProductInfoDownloaderTask task;

        public MyThread(EshopProductInfoDownloaderTask task) {
            super(task, task.getEshopType().name());
            this.task = task;
        }

        public void stopTask() {
            task.stopTask();
        }
    }
}
