import java.io.*;
import java.util.Calendar;

public class Main {
    public static void main(String[] args){
        System.out.println("Task 1:");
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/Book.txt"))) {
            BookParser.fill(reader);
        } catch (IOException e){
            System.out.println("Read error.");
        }
        System.out.println("Source text: ");
        BookParser.showSource();
        System.out.println("\nSwapping first and last word in each sentence: ");
        BookParser.swapFirstLastWords();
        BookParser.showResult();
        System.out.println("\nSorting words by ratio of syllables: ");
        BookParser.sortWordsBySyllables();
        BookParser.showResult();
        System.out.println("\nDeleting all occurences of first letter in each word: ");
        BookParser.deleteFirstLetter();
        BookParser.showResult();
        System.out.println("\nDeleting all N-letter words starting with consonants: ");
        BookParser.removeSpecifiedWords(7);
        BookParser.showResult();

        System.out.println("\nTask 2: ");

        CrazyLogger logger = new CrazyLogger();
        logger.addMessage("hello");
        logger.addMessage("my name is");
        logger.addMessage("test");
        logger.addMessage("and my name is");
        logger.addMessage("one more test");

        System.out.println("Logger contents: ");
        logger.show();

        Calendar time = Calendar.getInstance();

        System.out.println("\nSearch results for \"my name\": ");
        System.out.println(logger.search("my name"));

        System.out.println("\nSearch results for current time and date: ");
        System.out.println(logger.search(String.format("%1$td-%1$tm-%1$tY : %1$tH-%1$tM - ", time)));

        System.out.println("\nWriting search results for \"test\" to file.");
        try {
            FileWriter writer = new FileWriter("src/main/resources/searchTest.txt");
            logger.searchToStream("test", writer);
            writer.close();
        } catch (IOException e) {
            System.out.println("Output error.");
        }
        System.out.println("\nDumping log to file.");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/dumpTest.txt"));
            logger.dump(writer);
            writer.close();
        } catch (IOException e) {
            System.out.println("Output error.");
        }

        System.out.println("\nTask 3:");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader
            (new FileInputStream("src/main/resources/Article.html"), "CP1251"))) {
            ArticleParser.fill(reader);
        } catch (IOException e){
            System.out.println("Read error.");
        }
        System.out.println("Source text:");
        ArticleParser.showSource();

        System.out.println("\nSentences containing image tags: ");
        ArticleParser.searchForTags();
        ArticleParser.showResult();
    }
}
