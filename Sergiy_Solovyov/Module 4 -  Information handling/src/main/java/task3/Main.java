package task3;

import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 19.03.2016
 */
public class Main {
    public static void main(String[] args) {
        HTMLParser htmlParser = new HTMLParser();
        try {
            System.out.println(htmlParser.parseHTML("task3.html"));;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}


