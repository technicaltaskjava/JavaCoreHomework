package com.kokhanyuk.collections.readfile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class FileRead {

    Logger log = LoggerFactory.getLogger(FileRead.class);

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
