/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.naga.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.naga.models.EmailEntry;

/**
 *
 * @author NagNav
 */
public class ContentWriter
        implements Runnable {

    private static final String FILE_EXTENSION = ".txt";

    private String basePath;
    private EmailEntry entry;
    private ContentHelper contentHelper;
    private ContentWriteListener listener;

    public ContentWriter(String basePath, EmailEntry entry,
            ContentHelper contentHelper, ContentWriteListener listener) {
        this.basePath = basePath;
        this.entry = entry;
        this.contentHelper = contentHelper;
        this.listener = listener;
    }

    /**
     * Generates the path based on the date of the email
     *
     * @return
     */
    private String generateDirectoryPath() {
        return basePath + File.separator
                + MyUtil.extractYear(entry.getDate()) + File.separator
                + MyUtil.extractMonth(entry.getDate());
    }

    @Override
    public void run() {
        String content = entry.loadEmailBody(contentHelper);
        try {
            FileWriter fw;
            String dirPath = generateDirectoryPath();
            File file = new File(dirPath);
            // creating the dirs if not exists
            file.mkdirs();
            fw = new FileWriter(dirPath + File.separator + entry.getIndex() + FILE_EXTENSION);
            fw.append("Subject: " + entry.getSubject()).
                    append("\n\rAuthor: ").append(entry.getAuthor()).
                    append("\n\r").append(content);
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(ContentWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        listener.completed();
    }

    public void writeEmail(EmailEntry email) throws IOException {

    }
}
