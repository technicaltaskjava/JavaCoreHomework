package task1;

import console.WrongCharException;
import messages.Message;

import java.io.FileNotFoundException;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 16.03.2016
 */
public class Main {

private static Message m = new Message();

    public static void main(String[] args) throws FileNotFoundException {

        TextParser textParser = new TextParser();

        while (true){

           try {
               textParser.run();
           } catch (NumberFormatException e) {
               m.warn("Enter only numbers ");
               continue;
           } catch (WrongCharException e) {
               m.warn("Enter only one letter");
           }

       }
    }
}