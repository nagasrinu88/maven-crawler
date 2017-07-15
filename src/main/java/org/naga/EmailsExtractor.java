/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.naga;

import org.naga.util.ContentHelper;
import org.naga.util.MyUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.naga.modals.EmailEntry;
import org.naga.modals.MonthEntry;
import org.naga.modals.ResultSet;

/**
 *
 * @author Owner
 */
public class EmailsExtractor {

    private final String baseUrl;

    private final ContentHelper contentHelper;

    public EmailsExtractor(String baseUrl, ContentHelper contentHelper) {
        this.baseUrl = baseUrl;
        this.contentHelper = contentHelper;
    }

    public String getUrl() {
        return baseUrl;
    }

    public ResultSet extractForYear(int year) throws IOException {
        ResultSet results = new ResultSet(year);
        Element table = contentHelper.extractYearTable(baseUrl, year);
        if (table != null) {
            Elements rows = table.select("tbody tr");
            for (Element row : rows) {
                results.addEntry(extractMonth(row));
            }
        }
        return results;
    }

    /**
     * Parse The Month Entry and extract all the Email in that month
     *
     * @param monthRow
     * @return
     */
    private MonthEntry extractMonth(Element monthRow) {
        String date = monthRow.select(".date").text();
        String link = monthRow.select("a[href]").first().attr("href");
        MonthEntry entry = new MonthEntry(MyUtil.parseDate(date));
        entry.addEmails(extractEmails(entry.getYear(), link));
        return entry;
    }

    /**
     * checks whether the given table has next batch or not
     *
     * @param table
     * @return true if there is a next batch false otherwise
     */
    private boolean hasNextBatch(Element table) {
        Elements elements = table.select("thead");
        if (elements != null & elements.size() > 0) {
            Element thead = elements.first();
            Elements nextElements = thead.getElementsContainingText("Next ");
            return nextElements != null && nextElements.size() > 0;
        }
        return false;
    }

    /**
     * Extracts all the email in the specific month
     *
     * @param msgListTable
     * @return
     */
    private List<EmailEntry> extractEmails(int year, String path) {
        List<EmailEntry> emails = new ArrayList<>();
        boolean hasNext;
        String url = baseUrl + MyUtil.URL_PATH_SEPERATOR + path;
        String msgBoxUrl = url.substring(0, url.lastIndexOf(MyUtil.URL_PATH_SEPERATOR) + 1);
        int pageIndex = 0;
        int emailIndex = 0;
        // iterating over the batches
        do {
            Element table = contentHelper.extractEmailsTable(url + "?" + pageIndex);
            if (table != null) {
                Elements rows = table.select("tbody tr");
                for (Element row : rows) {
                    // shoudl be a valid email entry
                    if (row.select("a").size() > 0) {
                        emails.add(parseEmailEntry(year, emailIndex++, msgBoxUrl, row));
                    }
                }
                hasNext = hasNextBatch(table);
                pageIndex++;
            } else {
                hasNext = false;
            }
        } while (hasNext);

        return emails;
    }

    private EmailEntry parseEmailEntry(int year, int index, String url, Element row) {
        String author = row.select(".author").text();
        String subject = row.select(".subject").text();
        Date date = MyUtil.parseDateWithTime(row.select(".date").text());
        String link = row.select(".subject a[href]").first().attr("href");
        return new EmailEntry(index, author, subject, url + link, MyUtil.modifyYear(date, year));
    }
}
