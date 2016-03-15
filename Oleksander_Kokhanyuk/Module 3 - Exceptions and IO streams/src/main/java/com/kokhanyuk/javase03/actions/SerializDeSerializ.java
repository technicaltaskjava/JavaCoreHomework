package com.kokhanyuk.javase03.actions;

import com.kokhanyuk.javase03.data.MyList;

import java.io.*;

/**
 * SerializDeSerializ
 * <p/>
 * In this class implements methods
 * to serialize and deserialize.
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class SerializDeSerializ {
    public boolean serialization(MyList s, String fileName) {

        boolean flag = false;
        File f = new File(fileName);
        ObjectOutputStream ostream = null;
        try {
            FileOutputStream fos = new FileOutputStream(f);
            // if (fos != null) {
            ostream = new ObjectOutputStream(fos);
            ostream.writeObject(s);
            flag = true;
            //}
        } catch (FileNotFoundException e) {
            System.err.println("File can not be created: " + e);
        } catch (NotSerializableException e) {
            System.err.println("\n" + "Class does not support serialization: " + e);
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            try {
                if (ostream != null) {
                    ostream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed to open stream.");
            }
        }
        return flag;
    }

    public MyList deserialization(String fileName) throws InvalidObjectException {
        File fr = new File(fileName);
        ObjectInputStream istream = null;
        try {
            FileInputStream fis = new FileInputStream(fr);
            istream = new ObjectInputStream(fis);
            // десериализация
            MyList st = (MyList) istream.readObject();
            return st;
        } catch (ClassNotFoundException ce) {
            System.err.println("Class does not exist: " + ce);
        } catch (FileNotFoundException e) {
            System.err.println("File does not exist for deserialization " + e);
        } catch (InvalidClassException ioe) {
            System.err.println("Discrepancy between the versions of the classes: " + ioe);
        } catch (IOException ioe) {
            System.err.println("\n" + "Total I/O error: " + ioe);
        } finally {
            try {
                if (istream != null) {
                    istream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed to open stream.");
            }
        }
        throw new InvalidObjectException("Jbject is not restored.");
    }
}
