package task3and4;


import task3and4.myExeption.NotCreatedFilsTXT;

import java.io.*;

public class Task4
    {

        private String texts ="";
        Commands comad = new Commands();
        private String words;

        public void assayFile(File file, File newFile)
            {
                try
                    {

                        BufferedReader reader = new BufferedReader(new FileReader(file));
                        String s;
                        while ((s = reader.readLine()) != null)
                            {
                               texts += s;
                            }
                        reader.close();


                        words = comad.line(texts);

                        BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
                        writer.write(words);
                        writer.close();


                    }


                catch (NotCreatedFilsTXT e)
                    {
                        e.printStackTrace();
                    }
                catch (IOException e)
                    {
                        e.printStackTrace();
                    }






            }





    }
