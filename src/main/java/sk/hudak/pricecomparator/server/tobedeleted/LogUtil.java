package sk.hudak.pricecomparator.server.tobedeleted;

/**
 * Created by hudak on 07.06.2016.
 */
@Deprecated
public class LogUtil {

    public static void log(String msg) {
        System.out.println("[" +Thread.currentThread().getName() + "] " + msg);
    }
}
