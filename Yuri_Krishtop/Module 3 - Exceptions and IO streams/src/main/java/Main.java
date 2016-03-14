import com.epam.task02properties.*;
import com.epam.task01filesystem.*;
import com.epam.task05serialization.*;
import com.epam.tasks0304javawords.*;
import java.io.*;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

    //------------------------------------------------------------------------------------------------------------------
    //testing  tasks 01 filesystem.
        Viewer myVie = new Viewer();

    //------------------------------------------------------------------------------------------------------------------



    //------------------------------------------------------------------------------------------------------------------
    //testing  tasks 02 properties.
    /*
        FileProperties properties = new FileProperties("project.properties");
        properties.showProperties();
    */
    //------------------------------------------------------------------------------------------------------------------


    //------------------------------------------------------------------------------------------------------------------
    //testing  tasks 03 and 04 read/write from/to file, compare words in files with javawords.
    /*
        WordsFromText myWords = new WordsFromText("test.txt");
        try {
            myWords.writeWordsToFile("javaWordsFromText.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        WordsFromStream myWordsStream = new WordsFromStream("test.bat");
        try {
            myWordsStream.writeWordsToFile("javaWordsFromStream.bat");
        } catch (IOException e) {
            e.printStackTrace();
        }
     */
     //-----------------------------------------------------------------------------------------------------------------


      //----------------------------------------------------------------------------------------------------------------
      //testing task05 functionality of collection
      /*
        Actor actor01 = new Actor("Brad", "Pitt");
        Actor actor02 = new Actor("Angelina", "Jolie");
        Actor actor03 = new Actor("Johnny", "Depp");
        Actor actor04 = new Actor("Edward", "Norton");
        Actor actor05 = new Actor("Helena", "Carter");
        Film film01 = new Film("Mr. & Mrs. Smith", actor01, actor02);
        Film film02 = new Film("The Tourist", actor02, actor03);
        Film film03 = new Film("Fight Club", actor01, actor04, actor05);
        Collection myCollection = new Collection("Superfilms", film01, film02);
        System.out.println(myCollection);
        myCollection.addFilm(film03);
        System.out.println(myCollection);
        myCollection.deleteFilm(1);
        System.out.println(myCollection);
        myCollection.renameFilm(1, "New Film");
        System.out.println(myCollection);
        myCollection.deleteActor(1, 1);
        System.out.println(myCollection);
        myCollection.addActor(1, actor02);
        System.out.println(myCollection);
        myCollection.renameActor(1, 1, "newName");
        myCollection.resurnameActor(1, 1, "newSurname");
        //testing task05 serialization
        System.out.println(myCollection);
        String fileName = "./src/main/java/com/epam/task05serialization/serCollection";
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(myCollection);
            oos.close();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
      */

      //testing task05 deserialization
      /*
        String fileName = "./src/main/java/com/epam/task05serialization/serCollection";
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (fis.available() > 0 ) {
                Collection myCol = (Collection) ois.readObject();
                System.out.println(myCol);
            }
        } catch ( IOException | ClassNotFoundException e ) {
            e.printStackTrace();
        }
      */
      //----------------------------------------------------------------------------------------------------------------
    }
}
