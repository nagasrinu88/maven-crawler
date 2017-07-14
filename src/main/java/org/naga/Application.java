/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.naga;

import org.naga.util.ContentHelper;
import org.naga.util.ContentHelperImpl;
import java.io.IOException;
import org.naga.modals.MonthEntry;
import org.naga.modals.ResultSet;

/**
 *
 * @author Owner
 */
public class Application {

    public static void main(String[] args) throws IOException {
        String URL = "http://mail-archives.apache.org/mod_mbox/maven-users";

        ContentHelper contentHelper = new ContentHelperImpl();
        EmailsExtractor extractor = new EmailsExtractor(URL, contentHelper);
        ResultSet results = extractor.extractForYear(2015);

        System.out.println(results);
        for (MonthEntry entry : results.getEntries()) {
            System.out.println(entry.getEmails().get(10).loadEmailBody(contentHelper));
        }
    }
}
