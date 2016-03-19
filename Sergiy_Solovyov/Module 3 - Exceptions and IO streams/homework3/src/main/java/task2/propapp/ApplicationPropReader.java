package task2.propapp;

import console.Console;
import messages.Message;
import task2.exceptions.ExitException;
import task2.exceptions.KeyNotFoundException;
import task2.propreader.PropertiesReader;

import java.util.HashMap;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 10.03.2016
 */
public class ApplicationPropReader {

    private Console console = new Console();
    private Message m = new Message();

    public void run() throws ExitException {

        System.out.println("Welcome to PropertiesReader");

        while (true){

            m.message("If you want to exit print \"exit\"");
            m.message("Enter name of the file you want to find without \".properties\"");
            PropertiesReader reader = new PropertiesReader();
            String input = console.stringInput();

            if (input.equalsIgnoreCase("exit")){
                throw new ExitException("exit");
            }
            if (!input.endsWith(".properties")) {
                input = input+".properties";}

            HashMap<String, String> prop = reader.readProperties(input);

            if (prop == null) {continue;}

             while (true){

               m.message("Enter key to find the value in file: " + input);
               m.message("To return to main menu enter \"back\"");
               String inp = console.stringInput();

                 if (inp.equalsIgnoreCase("back")) {break;}

                 try {
                     System.out.println(reader.getValue(prop, inp));
                 } catch (KeyNotFoundException e) {
                     m.warn(e.getMessage());
                 }
             }

    }
    }
}
