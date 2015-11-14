package com.eventwebapp.util;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by xavier on 11/13/15.
 */
public class DateConverter {

    public static Date convert(LocalDate localDate){
        int day = localDate.getDayOfMonth();
        int month = localDate.getMonthValue()-1;
        int year = localDate.getYear();
        Calendar date = Calendar.getInstance();
        date.set(year, month, day, 0, 0, 0);

        return date.getTime();
    }

    public static boolean compare(Date date1, Date date2){
        boolean same;
        same = date1.getDate() == date2.getDate() &&
                date1.getMonth() == date2.getMonth() &&
                date1.getYear() == date2.getYear();

        return same;
    }
}
