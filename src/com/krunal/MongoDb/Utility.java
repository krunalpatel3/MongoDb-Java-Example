package com.krunal.MongoDb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Utility {


    /**
     * Get Date from string dd-mm-yyyy.
     *
     * @param dateString
     * @return Date.
     */
    public static Date getDateFromDD_MM_YYYY(String dateString) {
        Date date = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

            date = formatter.parse(dateString);
        } catch (Exception e) {
            System.out.println("Exception getDateFromDD_MM_YYYY:- " + e.getMessage());
        }
        return date;
    }

    /**
     * Get dd-mm-yyyy from Date.
     *
     * @param date
     * @return String.
     */
    public static String getDD_MM_YYYYFromDate(Date date) {
        String strDate = "";
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            strDate = formatter.format(date);
        } catch (Exception e) {
            System.out.println("Exception getDD_MM_YYYYFromDate:- " + e.getMessage());
        }
        return strDate;
    }


    /**
     * This Method convert dd/MM/YYYY string to yyyy-MM-dd string
     * because for querying date in MongoDb it needs yyyy-MM-dd string.
     * not dd/MM/YYYY string.
     * @param dd_MM_YYYY
     * @return String.
     */
    public static String queryDateConversion(String dd_MM_YYYY) {
        String strDate = "";
        try {
            strDate = new SimpleDateFormat("yyyy-MM-dd")
                    .format(getDateFromDD_MM_YYYY(dd_MM_YYYY));

        } catch (Exception e) {
            System.out.println("Exception queryDateConversion:- " + e.getMessage());
        }
        return strDate;
    }



}
