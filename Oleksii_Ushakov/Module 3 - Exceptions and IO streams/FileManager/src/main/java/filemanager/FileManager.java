package filemanager;

import filemanager.exceptions.FileCanReadException;
import filemanager.exceptions.FileNotCreatedException;

import java.io.*;

/**
 * @author Alexey Ushakov
 */
public class FileManager {
    public static final String STOP_STRING = "stop";

    public void createFile(File file) throws IOException {
        if (!file.createNewFile()) {
            throw new FileNotCreatedException("Can`t create file " + file.getCanonicalPath());
        }
    }

    public void deleteFile(File file) throws IOException {
        if (!file.delete()) {
            throw new FileNotFoundException("Can`t delete file " + file.getCanonicalPath());
        }

    }

    public void editFile(File file, InputStream in, boolean notOwerride)
            throws IOException {

        if ((file.exists()) && (!file.canRead())) {
            throw new FileCanReadException("Can`t read file " + file.getCanonicalPath());
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        try (PrintWriter writer = new PrintWriter(new FileOutputStream(file.getCanonicalPath(), notOwerride))) {

            System.out.println("For exit input \"" + STOP_STRING + "\"");
            String inputLine = reader.readLine();
            while (!inputLine.equals(STOP_STRING)) {
                writer.write((inputLine + '\n'));
                inputLine = reader.readLine();
            }

            writer.close();
//            reader.close(); /* Don`t close. reader == System.in. Otherwise exit */
        }
    }
}
