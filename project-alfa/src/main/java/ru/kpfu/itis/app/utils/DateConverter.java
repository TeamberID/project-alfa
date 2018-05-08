package ru.kpfu.itis.app.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

public class DateConverter {

    public static String getISO8601StringForDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(date);
    }

    public static String getShortDateStringForDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(date);
    }
}
