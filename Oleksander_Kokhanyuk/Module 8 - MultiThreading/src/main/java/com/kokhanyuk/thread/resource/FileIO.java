package com.kokhanyuk.thread.resource;

import org.apache.log4j.Logger;

import java.io.*;

/**
 * FileIO
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class FileIO {
    private static Logger log = Logger.getLogger(ToRunBank.class);

    public String readFile(String fileName) {
        String strOut = "";
        File fp = new File(fileName);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if (fp.exists() && fp.isFile()) {
            int b;
            try (FileInputStream is = new FileInputStream(fp)) {
                while ((b = is.read()) != -1) {
                    baos.write(b);
                }
                strOut = baos.toString("UTF-8");
            } catch (IOException e) {
                log.warn("Error reading file: " + fileName, e);
            }
        } else {
            log.warn("File not found");
        }
        return strOut;
    }
}