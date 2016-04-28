package strategy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sergey Solovyov
 */
public class PhoneNumberFinder implements TextParser {

    private static final Pattern PATTERN = Pattern.compile("(\\+38)\\(\\d{2,3}(\\))\\d{2,3}-\\d{2,3}-\\d{2,3}|\\d{2,4}-\\d{2,3}-\\d{2,3}-\\d{2,3}");
    @Override
    public String parse(String text) {
        Matcher matcher = PATTERN.matcher(text);
        StringBuilder builder = new StringBuilder();
        while (matcher.find()) {
            builder.append(matcher.group());
            builder.append("\n");
        }
        return builder.toString();
    }
}
