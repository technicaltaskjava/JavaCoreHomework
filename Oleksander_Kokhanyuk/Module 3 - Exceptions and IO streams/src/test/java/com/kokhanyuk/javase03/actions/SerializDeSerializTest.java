package com.kokhanyuk.javase03.actions;

import com.kokhanyuk.javase03.data.MyList;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 *  testSerialization
 *
 * Class SerializDeSerializ testing
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class SerializDeSerializTest {

    @Test
    public void testSerialization() throws Exception {
//Serialization current version MiList defined values
        String fileName = "iodata" + File.separator + "serialization.bin";
        MyList listFilms = new MyList(5);
        listFilms.addLine("Matrix", "Keanu Reeves");
        listFilms.addLine("Need for Speed", "Vin Diesel");
        listFilms.addLine("Matrix Revolution", "Keanu Reeves");
        SerializDeSerializ si = new SerializDeSerializ();
        assertTrue(si.serialization(listFilms, fileName));
    }

    @Test
    public void testDeserialization() throws Exception {
        String fileName = "iodata" + File.separator +"serialization.bin";
        SerializDeSerializ si = new SerializDeSerializ();
        MyList testListFilms = si.deserialization(fileName);
        if (!(testListFilms instanceof MyList)) {
            fail();
        }
    }
}