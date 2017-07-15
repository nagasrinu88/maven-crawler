/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.naga.models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Owner
 */
public class MonthEntry {

    private final int year;
    private final int month;
    private final List<EmailEntry> emails = new ArrayList<>();

    public MonthEntry(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        this.year = cal.get(Calendar.YEAR);
        this.month = cal.get(Calendar.MONTH);
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public List<EmailEntry> getEmails() {
        return emails;
    }

    public MonthEntry addEmails(Collection<EmailEntry> emails) {
        this.emails.addAll(emails);
        return this;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + this.year;
        hash = 11 * hash + this.month;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MonthEntry other = (MonthEntry) obj;
        if (this.year != other.year) {
            return false;
        }
        return this.month == other.month;
    }

    @Override
    public String toString() {
        return "MonthEntry{" + "year=" + year + ", month=" + month + ", totalEmails=" + emails.size() + '}';
    }

}
