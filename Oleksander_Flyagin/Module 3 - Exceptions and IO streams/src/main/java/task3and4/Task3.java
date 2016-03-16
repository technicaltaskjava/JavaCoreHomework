package task3and4;



import java.io.*;

public class Task3
    {
        private String texts = "";
        Commands comad = new Commands();
        private String words;

        public void assayFile(File file, File newFile) throws IOException
            {
                InputStream reader = null;
                OutputStream write = null;

                        reader = new FileInputStream(file);
                        int bayt = -1;

                        while ((bayt = reader.read()) != -1)
                            {

                                texts += String.valueOf((char) bayt);
                            }
                        reader.close();
                        words = comad.line(texts);

                        write = new FileOutputStream(newFile);

                        char[] letter = words.toCharArray();
                        for (int stap = 0; stap < words.toCharArray().length; stap++)
                            {
                                write.write(letter[stap]);
                            }
                    }



            }









