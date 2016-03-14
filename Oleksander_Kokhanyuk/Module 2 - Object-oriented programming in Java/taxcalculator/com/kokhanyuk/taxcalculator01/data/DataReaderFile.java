/**
 * DataReaderFile
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
package com.kokhanyuk.taxcalculator01.data;

import java.io.*;

import static java.io.File.separator;

public class DataReaderFile {

    private String DataString = "";

    String fileName = "com" + separator + "kokhanyuk" + separator + "taxcalculator01" + separator + "datafile" +
            separator + "PetrenkoIvan.txt";

    public String[] getDataFile(int sizeArray) {
        this.readDataFile();
        String[] s = new String[sizeArray];
        String[] s1 = this.DataString.split(" ");
        for (int i = 0; i < s.length; i++) {
            s[i] = s1[i];
        }
        return s;
    }

    void readDataFile() {
        File fp = new File(fileName);
        if (fp.exists()) {
            if (fp.isFile()) {
                BufferedReader br = null;
                try {
                    br = new BufferedReader(new FileReader(fileName));
                    String tmp = "";
                    while ((tmp = br.readLine()) != null) {
                        String[] s = tmp.split("\\s");
                        for (String res : s) {
                            readDataFile(res);
                        }
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
            } else {
                System.out.println("File " + fp.getName() + " not found");
            }
        }
    }

    void readDataFile(String variable) {
        this.DataString = this.DataString + variable + " ";
    }
}
