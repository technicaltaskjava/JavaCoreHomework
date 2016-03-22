package console;


import messages.Message;
import task1.Letters;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 09.03.2016
 */
public class Console {

    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private Message m = new Message();

    public int  intInput(){

        String str = null;
        int input = 0;
        do {
            try {
                str = bufferedReader.readLine().trim();
                input = Integer.valueOf(str);

            } catch (IOException e) {
              m.warn("Enter only number");
                throw new NumberFormatException();
            }
        }while (str.isEmpty());
        return input;
    }
    public  char  charInput() throws WrongCharException {

        String str = null;
        char ch = 0;
        do {
            try {
                str = bufferedReader.readLine().trim();
                ch = str.charAt(0);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }while (str.isEmpty());

        return ch;
    }

}
