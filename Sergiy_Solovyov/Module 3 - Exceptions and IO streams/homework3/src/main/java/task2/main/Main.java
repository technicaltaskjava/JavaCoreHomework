package task2.main;

import task2.exceptions.ExitException;
import task2.propapp.ApplicationPropReader;
import task2.propreader.PropertiesReader;

import java.io.IOException;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 10.03.2016
 */
public class Main {
    public static void main(String[] args) throws IOException {
        ApplicationPropReader applicationPropReader = new ApplicationPropReader();
        try {
            applicationPropReader.run();
        } catch (ExitException e) {
            System.out.println("Bye-bye");
        }
    }
}
