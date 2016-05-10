package utility;

import model.conf.Configuration;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.security.InvalidKeyException;
import java.util.concurrent.BlockingQueue;

import static org.junit.Assert.assertEquals;

public class SqlScriptParserTest {
	private static final Logger logger = LoggerFactory.getLogger(SqlScriptParserTest.class);

	@Test
	public void testParse() {
		try {
			String sqlScriptFile = Configuration.getInstance().getProperty("sql.init");
			final BlockingQueue<String> sqlScripts = SqlScriptParser.parse(sqlScriptFile);
			assertEquals(22, sqlScripts.size());
		} catch (InvalidKeyException | FileNotFoundException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Test(expected = FileNotFoundException.class)
	public void testParseFileNotFound() throws FileNotFoundException {
		SqlScriptParser.parse("");
	}
}