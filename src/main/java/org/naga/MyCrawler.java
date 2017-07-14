/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.naga;

import java.io.IOException;
import java.util.List;
import org.naga.modals.MonthEntry;

/**
 *
 * @author Owner
 */
public class MyCrawler {

    public static void main(String[] args) throws IOException {
        String URL = "http://mail-archives.apache.org/mod_mbox/maven-users/";

        EmailsExtractor extractor = new EmailsExtractor(URL);
        List<MonthEntry> entries = extractor.extractForYear(2015);
        for (MonthEntry entry : entries) {
            System.out.println(entry);
        }
    }
}
