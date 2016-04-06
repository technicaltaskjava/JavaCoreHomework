package com.task5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @author Sergey Solovyov
 */
public class CollectionsInfo {

    private static final Logger LOGGER = LoggerFactory.getLogger(CollectionsInfo.class);

    public void readFromFile(String fileName){

        File file = new File(fileName + ".txt");

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
