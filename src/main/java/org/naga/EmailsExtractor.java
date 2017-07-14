/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.naga;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.naga.modals.EmailEntry;
import org.naga.modals.MonthEntry;

/**
 *
 * @author Owner
 */
public class EmailsExtractor {

    private final String url;
    private Document document;
    private final char URL_PATH_SEPERATOR = '/';

    public EmailsExtractor(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public List<MonthEntry> extractForYear(int year) throws IOException {
        List<MonthEntry> entries = new ArrayList<>();
        if (document == null) {
            document = Jsoup.connect(url).get();
        }
        Element table = document.getElementsMatchingOwnText("Year " + year).parents().select(".year").first();
        Elements rows = table.select("tbody tr");
        for (Element row : rows) {
            entries.add(extractMonth(row));
        }

        return entries;
    }

    /**
     * Parse The Month Entry and extract all the Email in that month
     *
     * @param monthRow
     * @return
     */
    private MonthEntry extractMonth(Element monthRow) throws IOException {
        String date = monthRow.select(".date").text();
        String link = monthRow.select("a[href]").first().attr("href");
        MonthEntry entry = new MonthEntry(MyUtill.parseDate(date));
        //System.out.println(entry + " : " + url + URL_PATH_SEPERATOR + link);
        entry.addEmails(extractEmails(link));
        return entry;
    }

    /**
     * Extracts all the email in the specific month
     *
     * @param msgListTable
     * @return
     */
    private List<EmailEntry> extractEmails(String path) throws IOException {
        List<EmailEntry> emails = new ArrayList<>();
        Document monthDocument = Jsoup.connect(url + URL_PATH_SEPERATOR + path).get();
        if (monthDocument != null) {
            Element msgListTable = monthDocument.select("#msglist").first();
            Elements rows = msgListTable.select("tbody tr");
            for (Element row : rows) {
                //System.out.println("row = " + row.text());
                if (row.select("a").size() > 0) {
                    emails.add(parseEmailEntry(row));
                }

            }
        }

        return emails;
    }

    private EmailEntry parseEmailEntry(Element row) {
        String author = row.select(".author").text();
        String subject = row.select(".subject").text();
        String date = row.select(".date").text();
        return new EmailEntry(author, subject, MyUtill.parseDate2(date));
    }
}
