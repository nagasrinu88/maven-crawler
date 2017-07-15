/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.naga;

import org.naga.util.ContentHelper;
import org.naga.util.ContentHelperImpl;
import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.naga.models.EmailEntry;
import org.naga.models.ResultSet;
import org.naga.util.ContentWriteListener;
import org.naga.util.ContentWriter;

/**
 *
 * @author Owner
 */
public class Application
        implements ContentWriteListener {

    private static final int DEFAULT_POOL_SIZE = 10;
    private static final String DEFAULT_FOLDER = "maven-emails";

    private int completed;
    private String URL = "http://mail-archives.apache.org/mod_mbox/maven-users";
    private final ContentHelper contentHelper = new ContentHelperImpl();
    private int totalEmails;
    private String folder;
    private int poolSize;

    public Application() {
        this(DEFAULT_POOL_SIZE, DEFAULT_FOLDER);
    }

    public Application(int poolSize, String folder) {
        this.poolSize = poolSize;
        this.folder = folder;
    }

    @Override
    public synchronized void completed() {
        completed++;
        // printing batch wise to improve the performance
        if (completed % 100 == 0 || completed == totalEmails) {
            System.out.println("Fetched " + completed + " out of " + totalEmails + " Emails");
        }
    }

    public void fetch(int year) throws IOException {
        EmailsExtractor extractor = new EmailsExtractor(URL, contentHelper);
        ResultSet results = extractor.extractForYear(year);

        ExecutorService executor = Executors.newFixedThreadPool(poolSize);
        Queue<EmailEntry> queue = results.buildQueue();
        totalEmails = queue.size();
        while (!queue.isEmpty()) {
            executor.execute(new ContentWriter(folder, queue.poll(), contentHelper, this));
        }
        executor.shutdown();
    }

    public static void main(String[] args) throws IOException {
        int year = 2015; // you can change teh year value
        new Application().fetch(year);
    }
}
