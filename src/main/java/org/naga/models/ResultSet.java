/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.naga.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author NagNav
 */
public class ResultSet {

    private final int year;
    private final List<MonthEntry> entries = new ArrayList<>();

    public ResultSet(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public List<MonthEntry> getEntries() {
        return entries;
    }

    public void addEntry(MonthEntry entry) {
        entries.add(entry);
    }

    public Queue buildQueue() {
        Queue<EmailEntry> queue = new ConcurrentLinkedQueue();
        for (MonthEntry entry : entries) {
            queue.addAll(entry.getEmails());
        }
        return queue;
    }

    @Override
    public String toString() {
        return "ResultSet{" + "year=" + year + ", entries=" + entries.size() + '}';
    }

}
