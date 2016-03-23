package Task1;

import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Oleg on 21.03.2016.
 */
public class VowelComparator implements Comparator<String> {


    public int compare(String s1, String s2) {
        return vowelFound(s1) - vowelFound(s2);
    }

    public int vowelFound(String s) {
        Pattern p = Pattern.compile("[уеёыаоэюияaeiouyУЕЁЫАОЭAEIOUY]");
        Matcher m = p.matcher(s);
        int num = 0;

        while (m.find()) {
            num++;
        }
        return num;
    }
}
