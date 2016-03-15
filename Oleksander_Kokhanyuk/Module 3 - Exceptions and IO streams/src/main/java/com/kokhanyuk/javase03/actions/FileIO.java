package com.kokhanyuk.javase03.actions;

import java.io.*;

/**
 * FileIO
 *
 * This class performs input and output of information from/to file
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class FileIO {

    public static String readByteFile(String fileName) throws MyFileNotFound {
        String strOut = "";
        File fp = new File(fileName);
        if (fp.exists() && fp.isFile()) {
            int b;
            try (FileInputStream is = new FileInputStream(fp)) {
                while ((b = is.read()) != -1) {
                    strOut = strOut + Character.toString((char) b);
                }
            } catch (IOException e) {
                System.err.println("Error reading file: " + fileName);
            }
        } else {
            throw new MyFileNotFound(fp.getName());
        }
        return strOut;
    }

    public static String readSymbFile(String fileName) throws MyFileNotFound {
        String strOut = "";
        File f = new File(fileName);
        BufferedReader br = null;
        try {
            if (f.exists() && f.isFile()) {

                br = new BufferedReader(new FileReader(fileName));
                String tmp = "";
                while ((tmp = br.readLine()) != null) {
                    strOut = strOut + tmp + "\n";
                }
            } else {
                throw new MyFileNotFound(f.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return strOut;
    }

    public static void writeByteFile(String fileName, String text) {
        File f = new File(fileName);
        FileOutputStream fs;
        try {
            fs = new FileOutputStream(f);
            fs.write(text.getBytes());
        } catch (IOException e) {
            System.err.println("Error open stream " + e);
        }
    }

    public static void writeSymbFile(String fileName, String text, boolean saveOldData) {
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