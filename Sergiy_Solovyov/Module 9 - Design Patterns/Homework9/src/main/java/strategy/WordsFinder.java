package strategy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sergey Solovyov
 */
public class WordsFinder implements TextParser {

    private static final Pattern PATTERN = Pattern.compile("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b");

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
