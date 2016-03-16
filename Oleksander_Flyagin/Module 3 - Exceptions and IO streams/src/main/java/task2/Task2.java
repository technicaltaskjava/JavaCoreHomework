package task2;

import task3and4.myExeption.NotCreatedFilsTXT;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

public class Task2
    {
        String directori;
        public Task2(String directori)
            {
               this.directori = directori;

            }
        public  Task2()
            {

            }


        public void getProperties()
            {
                FileInputStream read;
                Properties property = new Properties();

                try
                    {
                        read = new FileInputStream(directori);
                        property.load(read);

                        String host = property.getProperty("db.host");
                        String login = property.getProperty("db.login");
                        String password = property.getProperty("db.password");

                        System.out.println("HOST: " + host + "\r\n"
                                + "LOGIN: " + login + "\r\n"
                                + "PASSWORD: " + password);
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


        public String getProperties(File file)
            {
                String resault ="";
                FileInputStream read;
                Properties property = new Properties();

                try
                    {
                        read = new FileInputStream(file);
                        property.load(read);

                        String host = property.getProperty("db.host");
                        String login = property.getProperty("db.login");
                        String password = property.getProperty("db.password");
                        resault = "HOST: " + host + "\r\n"
                                + "LOGIN: " + login + "\r\n"
                                + "PASSWORD: " + password;


                    }
                catch (NotCreatedFilsTXT e)
                    {
                        e.printStackTrace();
                    }
                catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                return resault;
            }



        public void showInfoProperties()
            {
                Scanner info = null;
                try
                    {
                        info = new Scanner(new File(directori));
                        String[] properties = new String[20];
                        String[] keys = new String[20];
                        int indax = 0;
                        while (info.hasNextLine())
                            {
                                String line = info.nextLine();
                                String[] lineSplit = line.split("=");
                                keys[indax] = lineSplit[0];
                                properties[indax] = lineSplit[1];
                                indax++;

                            }
                       for (int stap = 0; stap < 20; stap++)
                            {
                                if(properties[stap] == null)
                                    {
                                        break;

                                    }
                                System.out.println(properties[stap]);


                            }

                    }
                catch (FileNotFoundException e)
                    {
                        e.printStackTrace();
                    }

            }
        public String  showInfoProperties(File file)
            {
                String resault ="";
                Scanner info = null;
                try
                    {
                        info = new Scanner(file);
                        String[] properties = new String[20];
                        String[] keys = new String[20];
                        int indax = 0;
                        while (info.hasNextLine())
                            {
                                String line = info.nextLine();
                                String[] lineSplit = line.split("=");
                                keys[indax] = lineSplit[0];
                                properties[indax] = lineSplit[1];
                                indax++;

                            }
                        for (int stap = 0; stap < 20; stap++)
                            {
                                if(properties[stap] == null)
                                    {
                                        break;

                                    }

                                resault += properties[stap];


                            }

                    }
                catch (FileNotFoundException e)
                    {
                        e.printStackTrace();
                    }
                return resault;

            }
    }
