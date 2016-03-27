package task3;

import java.io.*;



public class Main {
    private static final String FILE="src\\main\\\\resources\\4. Information handling_task_attachment.html";

    public static void main(String[] args) throws FileNotFoundException {

    Search search=new Search();
        String text=search.readFile(FILE);
      // System.out.println(text);
        System.out.println(search.vuv(text));
     //  search.rege(text);



    }
}


