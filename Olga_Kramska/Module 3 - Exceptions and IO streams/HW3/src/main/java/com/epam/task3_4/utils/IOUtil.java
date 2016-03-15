package com.epam.task3_4.utils;

import com.epam.task3_4.Result;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Olga Kramska on 12-Mar-16.
 */
public class IOUtil {

    private IOUtil() {
    }

    public static String readFromFile(String fileName) throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(fileName);
        if (is == null) {
            throw new IllegalArgumentException("File not exist: " + fileName);
        }

        String result = null;
        StringBuilder in = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                in.append(line);
            }
            result = in.toString();
        } catch (IOException ex) {
            throw new IOException("Can not read file: " + fileName);
        } finally {
            is.close();
        }
        return result;
    }

    public static void writeToFile(String outFilePath, Result[] results) throws IOException {
        File outFile = new File(outFilePath);
        boolean createdFile = false;
        if (!outFile.exists()) {
            createdFile = outFile.createNewFile();
        }

        try (BufferedWriter out = new BufferedWriter(new FileWriter(outFile))) {
            if (!createdFile) {
                out.flush();
            }
            for (Result result : results) {
                out.write(result.toString());
            }
            System.out.println("Search results was successfully written to the " + outFile.getName() + " file");
        }
    }

    public static String readFromFileByByte(String fileName) throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(fileName);
        if (is == null) {
            throw new IllegalArgumentException("File not exist: " + fileName);
        }
        String result = null;
        StringBuilder in = new StringBuilder();
        try (BufferedInputStream inputStream = new BufferedInputStream(is)) {
            int ch;
            while ((ch = inputStream.read()) != -1) {
                in.append((char) ch);
            }
            result = in.toString();
        } catch (IOException ex) {
            throw new IOException("Can not read file: " + fileName);
        } finally {
            is.close();
        }
        return result;
    }

    public static void writeToFileByByte(String outFilePath, Result[] results) throws IOException {
        File outFile = new File(outFilePath);
        boolean createdFile = false;
        if (!outFile.exists()) {
            createdFile = outFile.createNewFile();
        }
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFile))) {
            if (!createdFile) {
                out.flush();
            }
            for (Result result : results) {
                out.write(result.toString().getBytes());
            }
            System.out.println("Search results was successfully written to the " + outFile.getName() + " file");
        }
    }
}
