package task3;

import task1.reader.FilesReader;
import task3.serchWord.SerchWord;

import java.io.File;
import java.io.FileReader;

public class Main
    {

        public static void main(String[] args)
            {
                File file = new File("src\\main\\java\\task3\\4. Information handling_task_attachment.html");
                FilesReader readerHTML = new FilesReader(file);
                SerchWord serchWord = new SerchWord();
                String text = readerHTML.reader();
                System.out.println(text);
                System.out.println("SERCH---------------------------------");
                serchWord.grepLine(text);

            }


    }
