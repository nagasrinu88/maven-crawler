/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.naga.util;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author NagNav
 */
public class ContentHelperImpl
        implements ContentHelper {

    private Document getDocument(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException ex) {
            Logger.getLogger(ContentHelperImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Element extractYearTable(String url, int year) {
        Document document = getDocument(url);
        if (document != null) {
            Element table = document.getElementsMatchingOwnText("Year " + year).parents().select(".year").first();
            return table;
        }
        return null;
    }

    @Override
    public Element extractEmailsTable(String url) {
        Document document = getDocument(url);
        if (document != null) {
            return document.select("#msglist").first();
        }
        return null;
    }

    @Override
    public String extractEmailContents(String url) {
        Document document = getDocument(url);
        if (document != null) {
            return document.select("#msgview .contents td pre").first().html();
        }
        return "";
    }

}
