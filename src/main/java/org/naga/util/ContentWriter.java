/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.naga.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import org.naga.modals.EmailEntry;

/**
 *
 * @author NagNav
 */
public class ContentWriter {

    OutputStream out;

    public static void writeTo(String folder) {

    }

    public void writeEmail(EmailEntry email) throws IOException {
        FileWriter fw = new FileWriter("a.txt");
        fw.append("Subject: " + email.getSubject()).
                append("\nAuthor: ").append(email.getAuthor()).
                append("\n");
        fw.flush();
        fw.close();
    }

}
