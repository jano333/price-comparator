package sk.hudak.pricecomparator.middle.utils;

import sk.hudak.pricecomparator.middle.exeption.PriceComparatorException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jan on 12. 1. 2016.
 */
public class DateUtils {

    public static final String DATE_FORMAT_HH_MM_YYYY = "dd.MM.yyyy";

    public static Date parseDate(String strDate, String format) {
        if (strDate == null) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(strDate);
        } catch (ParseException e) {
            throw new PriceComparatorException("error while parsing string to date", e);
        }
    }


    public static Date minusHours(Date inputDate, int hours) {
        if (inputDate == null) {
            return null;
        }
        long time = inputDate.getTime();
        long minus = hours * 60 * 60 * 1000;
        return new Date(time - minus);
    }
}
