package Task5;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Oleg on 14.03.2016.
 */
public class FilmCollectionOperation {

    public static void main(String[] args) {

        String path = WorkWithConsoleInput.enterPath();

        FilmCollection collection = new FilmCollection();
        fillCollection(collection);
        serializeCollection(collection, path);
        collection = deserializeCollection(path);
        System.out.println("___________________________________________________");

        Set<Actor> actors1 = new HashSet<>();
        actors1.add(new Actor("Test", "Test"));
        actors1.add(new Actor("Test1", "Test1"));
        actors1.add(new Actor("Test2", "Test2"));


        collection.addFilmToCollection(new Film("test3", actors1));

        serializeCollection(collection, path);

        FilmCollection collection1 = deserializeCollection(path);
        collection1.showFilmsInCollection();
        collection.removeFilmFromCollection("test");
        serializeCollection(collection, path);
        collection1 = deserializeCollection(path);
        System.out.println("_____________Deleted one film__________");
        collection1.showFilmsInCollection();
    }

    public static void serializeCollection(FilmCollection f, String path){
        FileOutputStream fo;
        try {
            fo = new FileOutputStream(path);
            ObjectOutputStream oo = new ObjectOutputStream(fo);
            oo.writeObject(f);
            oo.close();
            fo.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static FilmCollection deserializeCollection(String path) {
        FilmCollection coll = null;
        try {
            FileInputStream fi = new FileInputStream(path);
            ObjectInputStream oi = new ObjectInputStream(fi);
            coll = (FilmCollection) oi.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return coll;
    }

        public void showFilmCollection(FilmCollection filmCollection) {
            for (Film f : filmCollection.getFilmsSet()) {
                f.showAll();
            }
        }



    public static void fillCollection(FilmCollection filmCollection){
        Set<Actor> actors = new HashSet<>();
        actors.add(new Actor("Test", "Test"));
        actors.add(new Actor("Test1", "Test1"));
        actors.add(new Actor("Test2", "Test2"));

        filmCollection.addFilmToCollection(new Film("test", actors));
        filmCollection.addFilmToCollection(new Film("test1", actors));

    }

}
