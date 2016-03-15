package com.epam.javase03.task05.serializer;

import com.epam.javase03.task05.movie.Movie;

import java.io.*;

public class CollectionSaver {

    public void serializeCollection (Movie[] collection) {
        ObjectOutputStream outStream = null;
        try {
            outStream = new ObjectOutputStream(new FileOutputStream("collection.ser"));
            outStream.writeObject(collection);
            System.out.println("Serialization completed.\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (outStream != null) {
                    outStream.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void deserializeCollection() {
        ObjectInputStream inStream = null;
        try {
            inStream = new ObjectInputStream(new FileInputStream("collection.ser"));
            Object collectionRead = inStream.readObject();
            Movie[] collectionRestore = (Movie[]) collectionRead;
            System.out.println("Deserialization completed:");
            for (Movie movie : collectionRestore) {
                movie.printMovieInfo();
            }
        } catch (NotSerializableException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}

