/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.naga.modals;

import java.util.Date;

/**
 *
 * @author Owner
 */
public class EmailEntry {

    private final String author;
    private final String subject;
    private final Date date;

    public EmailEntry(String author, String subject, Date date) {
        this.author = author;
        this.subject = subject;
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public String getSubject() {
        return subject;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "EmailEntry{" + "author=" + author + ", subject=" + subject + ", date=" + date + '}';
    }

}
