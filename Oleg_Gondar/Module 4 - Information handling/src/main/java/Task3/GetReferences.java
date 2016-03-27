package Task3;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Oleg on 22.03.2016.
 */
public class GetReferences {

    public static void showReferences() throws IOException {

        String text = ConvertHtmlToText.convert();
        Pattern p = Pattern.compile("(^|(?<=[.!?]\\s\\t?))(\\d+\\.\\s\\t?)*[А-ЯA-Z][^!?]*?[.!?](?=\\s*(\\d+\\.\\s\\t?)*[А-ЯA-Z]|$)", Pattern.MULTILINE);
        Matcher m = p.matcher(text);
        while (m.find()) {
            if (m.group().matches("(.*[Р|р]ис\\..*)")) {
                System.out.println(m.group());
            }
        }


    }
}
