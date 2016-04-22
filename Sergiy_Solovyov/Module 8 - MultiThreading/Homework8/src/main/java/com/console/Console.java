package com.console;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * @author Sergey Solovyov
 */
public class Console {

    private static final Logger LOGGER = LoggerFactory.getLogger(Console.class);
    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public int  intInput(){

        int number = 1;
        String str = "";

        do {
            try {
                str = bufferedReader.readLine();
                number = Integer.parseInt(str);
            }

            catch (IOException|NumberFormatException e) {
                System.out.println("***********Please, enter only numbers without blanks**********");
                LOGGER.info(e.getMessage(), e);
            }
        } while (str.isEmpty());

        return number;

    }


}
