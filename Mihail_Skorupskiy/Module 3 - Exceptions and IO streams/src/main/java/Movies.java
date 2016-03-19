import textfiles.IOMethods;

import java.io.*;

public class Movies implements Serializable {
    private String[] actors;
    private String name;
    private int year = 0;
    private int titlePosition = 0;

    private static Movies[] collection;

    public static void initFromFile(){
        // initial Movies[] fill from "Movies.txt" file, any line with " is considered a movie title
        // next line is considered a year, all lines in between year and next title are considered actors.
        String[] buffer = null;
        try {
            buffer = IOMethods.readStringsFromFile("src/main/resources/Movies.txt");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        if (buffer != null) {
            int movieCount = 0;
            for (int i = 0; i < buffer.length; i++){
                if (buffer[i].contains("\"")){
                    movieCount++;
                }
            }
            collection = new Movies[movieCount];
            int manualIndex = 0;
            for (int i = 0; i < buffer.length; i++){
                if (buffer[i].contains("\"")){
                    collection[manualIndex] = new Movies();
                    collection[manualIndex].titlePosition = i;
                    collection[manualIndex].name = buffer[i];
                    collection[manualIndex].year = Integer.parseInt(buffer[i+1]);
                    manualIndex++;
                }
            }
            //filling actors[] for all movies except the last one
            for (int i = 0; i < collection.length - 1; i++){
                int actorsSize = collection[i+1].titlePosition - (collection[i].titlePosition + 2);
                collection[i].actors = new String[actorsSize];
                manualIndex = 0;
                for (int j = collection[i].titlePosition + 2; j < collection[i+1].titlePosition; j++){
                    collection[i].actors[manualIndex] = new String(buffer[j]);
                    manualIndex++;
                }
            }
            //filling actors[] for the last movie
            int actorsSize = buffer.length - (collection[collection.length-1].titlePosition+2);
            collection[collection.length-1].actors = new String[actorsSize];
            manualIndex = 0;
            for (int i = collection[collection.length-1].titlePosition+2; i < buffer.length; i++) {
                collection[collection.length-1].actors[manualIndex] = buffer[i];
                manualIndex++;
            }
        }
    }

    public static void writeToFile() throws IOException{ // serialization
        ObjectOutputStream serializer = null;
        try {
            serializer = new ObjectOutputStream(new FileOutputStream("src/main/resources/Buffer"));
        } catch (FileNotFoundException e){
            File buffer = new File("buffer");
            try {
                buffer.createNewFile();
                writeToFile();
            } catch (IOException ex){
                throw ex;
            }
        } catch (IOException e){
            throw e;
        }
        if (serializer != null){
            serializer.writeObject(collection);
        }
    }

    public static void readFromFile() throws IOException{ // deserialization
        reset();
        ObjectInputStream deserializer = null;
        try {
            deserializer = new ObjectInputStream(new FileInputStream("src/main/resources/Buffer"));
        } catch (FileNotFoundException e){
            System.out.println("File not found.");
        } catch (IOException e) {
            throw e;
        }
        if (deserializer != null){
            try {
                deserializer = new ObjectInputStream(new FileInputStream("src/main/resources/Buffer"));
                collection = (Movies[])deserializer.readObject();
            } catch (IOException e) {
                throw e;
            } catch (ClassNotFoundException e){
                System.out.println("Wrong file contents.");
            }
        }
    }

    public static void show(){
        if (collection != null){
            System.out.println("Collection:");
            for (int i = 0; i < collection.length; i++){
                System.out.println("\tMovie:\n\t\t" + collection[i].name);
                System.out.println("\tYear:\n\t\t" + collection[i].year);
                System.out.println("\tStarring: ");
                for (int j = 0; j < collection[i].actors.length; j++){
                    System.out.println("\t\t" + collection[i].actors[j]);
                }
            }
        } else {
            System.out.println("Collection is empty.");
        }
    }

    private static void reset(){
        collection = null;
    }
}
