package task1;

import jdk.internal.util.xml.impl.Input;

import java.io.*;
import java.nio.Buffer;
import java.util.Scanner;

public class Task1
    {

        public void createFile(File file)
            {
                 if (!testExistsFile(file))
                    {
                        try
                            {
                                file.createNewFile();
                                System.out.println("Create");
                            }
                        catch (IOException e)
                            {
                                e.printStackTrace();
                            }
                    }
                else
                    {
                        System.out.println("This file already exists");
                    }
            }


        public void createFile(String name)
            {
                File file = new File(name);
                if (!testExistsFile(file))
                    {
                        try
                            {
                                file.createNewFile();
                                System.out.println("Create");
                            }
                        catch (IOException e)
                            {
                                e.printStackTrace();
                            }
                    }
                else
                    {
                        System.out.println("This file already exists");
                    }
            }

        public void deletFile(String name)
            {
                File file = new File(name);
                if (testExistsFile( file))
                    {
                        System.out.println("File "+   file.getName() + " was delet" );
                        file.delete();

                    }
                else
                    {
                        System.out.println("This file " + "\"" + name + "\"" + "was delet befor");
                    }

            }

        public void writeToTxt(String name)
            {
                if(testExistsFile(new File(name)))
                    createFile(name);
                try
                    {
                        FileWriter writer = new FileWriter(name, true);
                        System.out.println("Eneter your message");

                        Scanner sc = new Scanner(System.in);
                        String message =  sc.nextLine();
                        writer.write(message);
                        writer.append('\n');
                        writer.flush();
                    }
                catch (IOException ex)
                    {
                        System.out.println(ex.getMessage());
                    }

            }

        public void readTxt(String name)
            {
                FileReader reader = null;
                try
                    {
                        reader = new FileReader(name);


                        int c;
                        while ((c = reader.read()) != -1)
                            {
                                System.out.print((char) c);
                            }
                    }

                catch (IOException e)
                    {
                        e.printStackTrace();
                    }

            }

        public void showTreeDirect(String name, int leve)
            {
                File file = new File(name);

                if(file.isDirectory())
                    {
                        System.out.println("Pakage " + file.getName());
                        File [] fileList = file.listFiles();
                        for (File files : fileList)
                            {
                                if(files.isDirectory())
                                    {
                                        showTreeDirect(name, leve++);
                                    }
                            }
                        for (File files : fileList)
                            {
                                if(files.isFile())
                                    {
                                        System.out.println("File " + file.getName());
                                    }
                            }

                    }
            }
        public void showTreeDirect(String name)
            {
                showTreeDirect(name, 0);
            }

        public void showFilesAndPakageInDirectory(File dir)
            {
                File[] files = dir.listFiles();
                for (File file : files)
                    {
                        if (file.isDirectory())
                            {
                                System.out.println("Pakage : " + file.getName());
                            }
                    }
                for (File file : files)
                    {
                        if (file.isFile())
                            {
                                System.out.println("File : " + file.getName());
                            }
                    }
            }

        public void testExistsFile(String name)
            {
              File file = new File(name);
              if (!testExistsFile(file))
                System.out.println("File " + " \"" +  name + "\" "+ " don't found ");
                else
                  {
                      System.out.println("File " + " \"" +  name + "\" " + " directori " + file.getAbsolutePath());
                  }
            }

        public boolean testExistsFile(File file)
            {
                boolean res = false;
                if(file.exists())
                    {
                        res = true;
                    }
                return res;
            }


    }

