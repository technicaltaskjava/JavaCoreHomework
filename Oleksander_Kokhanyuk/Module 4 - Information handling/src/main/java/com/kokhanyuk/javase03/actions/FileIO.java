package com.kokhanyuk.javase03.actions;

import java.io.*;

/**
 * FileIO
 * <p/>
 * This class performs input and output of information from/to file
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class FileIO {

    public String readFile(String fileName, String charset) {
        String strOut = "";
        File fp = new File(fileName);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if (fp.exists() && fp.isFile()) {
            int b;
            try (FileInputStream is = new FileInputStream(fp)) {
                while ((b = is.read()) != -1) {
                    baos.write(b);
                }
                strOut = baos.toString(charset);
            } catch (IOException e) {
                System.err.println("Error reading file: " + fileName);
            }
        } else {
            System.out.println("File not found");
        }
        return strOut;
    }

    public void writeFile(String fileName, String text, boolean saveOldData) {
        File f = new File(fileName);
        FileWriter fw;
        BufferedWriter bw;
        PrintWriter pw = null;
        try {
            fw = new FileWriter(f, saveOldData);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
            pw.printf(text + " \n");
        } catch (IOException e) {
            System.err.println("Error open stream " + e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }
}