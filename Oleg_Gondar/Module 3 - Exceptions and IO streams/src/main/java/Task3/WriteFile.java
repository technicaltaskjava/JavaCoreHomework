package Task3;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Created by Oleg on 15.03.2016.
 */
public class WriteFile {

    public static void writeStringToFile(String path, String textToWrite) {

        OutputStream os = null;
        try {
            os = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PrintStream printStream = new PrintStream(os);
        printStream.print(textToWrite);
        printStream.close();

    }

}
