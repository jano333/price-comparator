package sk.hudak.pricecomparator.server.utils;

import java.util.Date;

/**
 * Created by jan on 12. 1. 2016.
 */
public class DateUtils {


    public static Date minusHours(Date inputDate, int hours) {
        if (inputDate == null) {
            return null;
        }
        long time = inputDate.getTime();
        long minus = hours * 60 * 60 * 1000;
        return new Date(time - minus);
    }
}
