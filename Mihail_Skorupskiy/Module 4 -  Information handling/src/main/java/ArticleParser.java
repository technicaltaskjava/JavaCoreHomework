import misc.Patterns;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;

public class ArticleParser {
    private static StringBuilder source = new StringBuilder();
    private static StringBuilder result = new StringBuilder();

    public static void fill(BufferedReader reader) throws IOException {
        try {
            reader.mark(8388608); // - 8 MB in bytes
            int lineCount = 0;
            while (reader.readLine() != null) {
                lineCount++;
            }
            reader.reset();
            for (int i = 0; i < lineCount; i++) {
                source.append(reader.readLine());
            }
        } catch (IOException e){
            throw e;
        }
    }

    private static void normalizeWhiteSpaces(){
        removeHtmlTags();
        Matcher article = Patterns.space.matcher(source);
        source.replace(0, source.capacity(), article.replaceAll(" "));
    }

    public static void searchForTags(){
        String[] sentences = Patterns.sentenceLimiter.split(source);
        int number = 1;
        for (int i = 0; i < sentences.length; i++){
            Matcher tag = Patterns.tag.matcher(sentences[i]);
            if (tag.find()){
                result.append(number);
                result.append(". ");
                result.append(sentences[i]);
                result.append("\n\n");
                number++;
            }
        }
    }

    private static void removeHtmlTags(){
        normalizeWhiteSpaces();
        Matcher html = Patterns.htmlTag.matcher(source);
        source.replace(0, source.capacity(), html.replaceAll(""));
    }

    public static void showSource(){
        System.out.println(source.toString());
    }

    public static void showResult(){
        System.out.println(result.toString());
    }
}
