package task1.reader;

import java.io.*;

public class FilesReader
    {
        File file;
        public FilesReader(File file)
            {
                this.file = file;
            }

        public  String reader()
            {
                String text = "";

                try
                    {
                        BufferedReader reader = new BufferedReader(new java.io.FileReader(file.getAbsoluteFile()));
                        try
                            {
                                String temp;
                                while ((temp = reader.readLine())!= null)
                                text+=temp;

                            }
                        catch (IOException e)
                            {
                                e.printStackTrace();
                            }
                    }
                catch (FileNotFoundException e)
                    {
                        e.printStackTrace();
                    }

                return text;
            }
    }
