package javase04.common.handler;

import java.io.*;

/**
 * Base parent class with file input-output
 * Created by Yury Vislobodsky on 19.03.2016.
 */
public class FileHandler extends Handler {
    public boolean loadFromFile(String fileName) {
        boolean success = false;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(fileName);
            inputFromStream(fis);
            success = true;
        } catch (FileNotFoundException e) {
            System.out.println("File " + fileName + " not found");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return success;
    }

    public boolean saveToFile(String fileName) {
        boolean success = false;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fileName);
            outputToStream(fos);
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return success;
    }
}
