package patterninfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @author Sergey Solovyov
 */
public class PatternInfo {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatternInfo.class);

    public void readFromFile(String path){

        File file = new File(path);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF8"));){

            String currentLine;

            while ((currentLine = br.readLine()) != null) {

                System.out.println(currentLine);
            }

        } catch (IOException e) {
            LOGGER.info(e.getMessage(), e);
        }
    }


}
