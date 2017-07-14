/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.naga.util;

import org.jsoup.nodes.Element;

/**
 *
 * @author NagNav
 */
public interface ContentHelper {

    /**
     * Extracts the years DOM element from the given page url for the specific
     * year
     *
     * @param url
     * @param year
     * @return
     */
    Element extractYearTable(String url, int year);

    /**
     * Extracts the Emails table from the given page url
     *
     * @param url
     * @return
     */
    Element extractEmailsTable(String url);

    /**
     * Extracts actual email content from the given page url
     *
     * @param url
     * @return
     */
    String extractEmailContents(String url);
}
