/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.naga.models;

import java.util.Date;
import org.naga.util.ContentHelper;

/**
 *
 * @author Owner
 */
public class EmailEntry {

    private final int index;
    private final String author;
    private final String subject;
    private final String link;
    private final Date date;
    private String content;

    public EmailEntry(int index, String author, String subject, String link, Date date) {
        this.index = index;
        this.author = author;
        this.subject = subject;
        this.link = link;
        this.date = date;
    }

    public int getIndex() {
        return index;
    }

    public String getAuthor() {
        return author;
    }

    public String getSubject() {
        return subject;
    }

    public String getLink() {
        return link;
    }

    public Date getDate() {
        return date;
    }

    /**
     * Will load the actual email content by using contentHelper. if the content
     * is already loaded by some previous activity then it will return it with
     * out reloading it
     *
     * @param contentHelper
     * @return
     */
    public String loadEmailBody(ContentHelper contentHelper) {
        if (content == null) {
            content = contentHelper.extractEmailContents(link);
        }
        return content;
    }

    @Override
    public String toString() {
        return "EmailEntry{" + "author=" + author + ", subject=" + subject + ", date=" + date + '}';
    }

}
