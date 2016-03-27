package Task1;

import javafx.collections.transformation.SortedList;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Oleg on 20.03.2016.
 */
public class SentenceUtilites {

    public static Matcher getSenteces(String text) {

        Pattern p = Pattern.compile("(^|(?<=[.!?]\\s))(\\d+\\.\\s?)*[А-ЯA-Z][^!?]*?[.!?](?=\\s*(\\d+\\.\\s)*[А-ЯA-Z]|$)", Pattern.MULTILINE);
        Matcher m = p.matcher(text);
        return m;
    }

    private static String[] getWords() {

        Matcher m = getSenteces(ConvertDocx.getTextFromDocxFile());
        StringBuilder stringBuilder = new StringBuilder();


        while (m.find()) {

            stringBuilder.append(m.group());

        }
        return stringBuilder.toString().split("[ \\p{Punct}\\n\\s]");

    }

    public static String swapWordsInSentences() {

        Matcher m = getSenteces(ConvertDocx.getTextFromDocxFile());
        StringBuilder stringBuilder = new StringBuilder();
        String temp;

        while (m.find()) {

            String[] words = m.group().split("[ \\p{Punct}\\n\\s]");
            temp = words[0];
            words[0] = words[words.length - 1];
            words[words.length - 1] = temp;
            stringBuilder.append(Arrays.toString(words) + "\n");
            System.out.println(Arrays.toString(words));
        }
        return stringBuilder.toString();
    }

    public static void sortWordsInText() {

        String[] words = getWords();
        VowelComparator v = new VowelComparator();
        Arrays.sort(words, v);
        for (String w : words)
            System.out.println(w);


    }


    public static List<String> removeWords(int count) {

        String regexp = "([бвгджзйклмнпрстфхцчшщБВГДЖЗЙКЛМНПРСТФХЦЧШЩ]|[^aeiouyAEIOUY])([а-яА-Я]|[a-zA-Z]){" + (count - 1) + "}";
        String[] words = getWords();
        List<String> resultWords = new LinkedList<String>();
        for (String s :
                words) {
            if (!s.matches(regexp)) {
                resultWords.add(s);
            }
        }
        return resultWords;

    }

    public static void replaceLetters() {
        String[] words = getWords();
        for (String s :
                words) {

            if (s.length() != 0) {
                System.out.println(s.replaceAll(String.valueOf(s.charAt(0)), ""));
            }
        }

    }

    public static String replaceWhithSpaces(String text) {

        String pattern = "[\\s]{2,}|\\t+]";
        return text.replaceAll(pattern, " ");

    }

}
