package Task3;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

/**
 * Created by Oleg on 22.03.2016.
 */
public class ConvertHtmlToText {

    public static String convert() throws IOException {


        File input = new File("Java.SE.04. Information handling_task_attachment.html");

        Document doc = Jsoup.parse(input, "Cp1251", "");

        return doc.text();


    }
}
