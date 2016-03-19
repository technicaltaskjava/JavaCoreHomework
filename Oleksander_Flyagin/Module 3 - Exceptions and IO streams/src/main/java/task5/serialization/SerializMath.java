package task5.serialization;

import java.io.*;

public class SerializMath
    {

        public void serialazObject(Object serialaz)
            {
                try
                    {
                        FileOutputStream save = new FileOutputStream("src/Main/java/task5/serialization/save.txt");
                        ObjectOutputStream saveObject = new ObjectOutputStream(save);
                        saveObject.writeObject(serialaz);
                        saveObject.flush();
                        saveObject.close();

                    }
                catch (IOException e)
                    {
                        e.printStackTrace();
                    }
            }

        public Object deSerialazObject()
            {
                Object backup = null;


                try
                    {
                        ObjectInputStream readFile = new ObjectInputStream(new FileInputStream("src/Main/java/task5/serialization/save.txt"));
                        backup = (Object) readFile.readObject();
                    }
                catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                catch (ClassNotFoundException e)
                    {
                        e.printStackTrace();
                    }
                return backup;

            }

    }
