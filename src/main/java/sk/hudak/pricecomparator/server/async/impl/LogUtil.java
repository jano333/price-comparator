package sk.hudak.pricecomparator.server.async.impl;

/**
 * Created by hudak on 07.06.2016.
 */
@Deprecated
public class LogUtil {

    public static void log(String msg) {
        System.out.println("[" +Thread.currentThread().getName() + "] " + msg);
    }
}
