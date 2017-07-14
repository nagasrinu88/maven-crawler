/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.naga.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Owner
 */
public class MyUtil {

    private static final DateFormat DF1 = new SimpleDateFormat("MMM yyyy");
    private static final DateFormat DF2 = new SimpleDateFormat("EEE, d MMM, HH:mm");
    public static final char URL_PATH_SEPERATOR = '/';

    /**
     * Parses the given date string with the specified format object
     *
     * @param format
     * @param date
     * @return
     */
    private static Date parseDate(DateFormat format, String date) {
        if (date != null && !date.isEmpty()) {
            try {
                return format.parse(date);
            } catch (ParseException ex) {
                Logger.getLogger(MyUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    /**
     * It tries to parse the given date string to date object
     * <br />
     * Expected Format is: <b>MMM yyyy</b>
     * <br />
     * Example date is: <b>Jan 2015</b>
     *
     * @param date
     * @return
     */
    public static Date parseDate(String date) {
        return parseDate(DF1, date);
    }

    /**
     * It tries to parse the given date string to date object
     * <br />
     * Expected Format is: <b>EEE, d MMM, HH:mm</b>
     * <br />
     * Example date is: <b>Sat, 24 Jan, 02:40</b>
     *
     * @param date
     * @return
     */
    public static Date parseDateWithTime(String date) {
        return parseDate(DF2, date);
    }

}
