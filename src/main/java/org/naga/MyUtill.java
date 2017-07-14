/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.naga;

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
public class MyUtill {

    private static final DateFormat DF1 = new SimpleDateFormat("MMM yyyy");
    private static final DateFormat DF2 = new SimpleDateFormat("EEE, d MMM, HH:mm");

    private static Date parseDate(DateFormat format, String date) {
        if (date != null && !date.isEmpty()) {
            try {
                return format.parse(date);
            } catch (ParseException ex) {
                Logger.getLogger(MyUtill.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public static Date parseDate(String date) {
        return parseDate(DF1, date);
    }

    public static Date parseDate2(String date) {
        return parseDate(DF2, date);
    }

    public static void main(String[] args) {
        System.out.println("args = " + parseDate("Jan 2015"));
        System.out.println("args = " + parseDate2("Sat, 24 Jan, 02:40"));
    }
}
