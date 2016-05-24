package utility;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.BlockingQueue;

import static org.junit.Assert.assertEquals;

public class SqlScriptParserTest {
    private static final Logger logger = LoggerFactory.getLogger(SqlScriptParserTest.class);
    private static final String DB_INITIALIZATION_SQL = "db_initialization.sql";

    @Test
    public void testParse() {
        try {
            URL resource = ClassLoader.getSystemResource(DB_INITIALIZATION_SQL);
            URI sqlScriptFile = resource.toURI();
            final BlockingQueue<String> sqlScripts = SqlScriptParser.parse(sqlScriptFile);
            assertEquals(22, sqlScripts.size());
        } catch (FileNotFoundException | URISyntaxException e) {
            logger.error(e.getMessage(), e);
        }
    }
}