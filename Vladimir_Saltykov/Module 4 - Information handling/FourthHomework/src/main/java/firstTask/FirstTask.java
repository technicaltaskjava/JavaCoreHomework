package firstTask;


import java.io.IOException;

public class FirstTask {
    private static final String FILE_NAME = "textTOplay.txt";


    public static void main(String[] args) throws IOException {

        TextWorker handling = new TextWorker();

        String text = handling.reader(FILE_NAME);

        String text1 = handling.replaceWords(text);
        String text2 = handling.deleteWords(text);
        String text3 = handling.deleteFirstLetters(text);
        handling.writer("replacedWords.txt",text1);
        handling.writer("deletedWords.txt",text2);
        handling.writer("deletedLetters.txt",text3);
    }
}
