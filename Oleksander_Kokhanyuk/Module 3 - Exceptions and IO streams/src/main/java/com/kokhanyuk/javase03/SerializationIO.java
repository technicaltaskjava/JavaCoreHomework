package com.kokhanyuk.javase03;

import com.kokhanyuk.javase03.actions.SerializDeSerializ;
import com.kokhanyuk.javase03.data.MyList;

import java.io.File;
import java.io.InvalidObjectException;
import java.util.Scanner;

/**
 * SerializationIO
 * <p/>
 * This class holds deserialize object,
 * allows the use of an object,
 * then spend serialization object.
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class SerializationIO {
    public static void main(String[] args) throws InvalidObjectException {
        String fileName = "iodata" + File.separator + "serialization.bin";
        String actor, film, st;
        int n = 0;
        SerializDeSerializ si = new SerializDeSerializ();
        try (Scanner in = new Scanner(System.in)) {
            MyList listFilms = si.deserialization(fileName);
            System.out.println("Deserialization is made.");
            System.out.print(listFilms + "\n");
            while (n != 4) {
                System.out.println("1-Add Films and Actor;\n2-Delete Films and Actor;\n3-Show All\n4-Exit");
                st = in.nextLine();
                n = Integer.parseInt(st);
                switch (n) {
                    case 1:
                        System.out.println("Entering film name: ");
                        film = in.nextLine();
                        System.out.println("Entering actor name: ");
                        actor = in.nextLine();
                        listFilms.addLine(film, actor);
                        break;
                    case 2:
                        System.out.println("Number of deleted rows");
                        st = in.nextLine();
                        listFilms.deleteLine(st);
                        break;
                    case 3:
                        System.out.println(listFilms);
                        break;
                }
            }
            if (si.serialization(listFilms, fileName)) {
                System.out.println("Serialization is made.");
            } else {
                System.out.println("Serialization is not made.");
            }
        } catch (InvalidObjectException e) {
            System.out.println("Deserialization is not made.");
        } catch (NumberFormatException id1) {
            System.out.println("Invalid data format!");
        }
    }
}

