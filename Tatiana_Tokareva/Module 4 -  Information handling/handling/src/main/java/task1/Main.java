package task1;


import java.io.*;

public class Main {
    private static final String FILE_NAME = "src/main/resources/text.txt";


    public static void main(String[] args) throws IOException {

        HandlingText handling = new HandlingText();

        String text = handling.reader(FILE_NAME);

        String text1=handling.replaceWords(text);
        String text2 =handling.deleteWords(text);
        String text3 =handling.deleteFirstLetters(text);
        handling.writer("src/main//resources/text1.txt",text1);
        handling.writer("src/main//resources/text2.txt",text2);
        handling.writer("src/main//resources/text3.txt",text3);
    }
}



