package utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Pattern;

public class SqlScriptParser {
    private SqlScriptParser() {
    }

    public static BlockingQueue<String> parse(final URI sqlScript) throws FileNotFoundException {
        Validator.isNull(sqlScript);
        BlockingQueue<String> sqlList = new LinkedBlockingQueue<>();
        try (Scanner scanner = new Scanner(new File(sqlScript))) {
            scanner.useDelimiter(Pattern.compile("(;(\r)?\n)"));
            while (scanner.hasNext()) {
                sqlList.add(scanner.next());
            }
        }
        return sqlList;
    }
}
