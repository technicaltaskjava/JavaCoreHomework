package model.script;

import dao.impl.DaoFactoryImpl;
import exception.DaoException;
import model.conf.Configuration;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.InvalidKeyException;

import static org.junit.Assert.assertEquals;

public class SqlScriptLoaderTest {
	private static final Logger logger = LoggerFactory.getLogger(SqlScriptLoaderTest.class);

	@Test
	public void testLoad() throws DaoException {
		SqlScriptLoader loader = new SqlScriptLoader();
		Configuration conf = Configuration.getInstance();
		conf.load("src/test/resources/configuration_test.properties");
		DaoFactoryImpl.getInstance(true);
		String scriptFileName;
		try {
			scriptFileName = conf.getProperty("sql.fill.user");
			loader.getScript(scriptFileName);
		} catch (InvalidKeyException e) {
			logger.error(e.getMessage(), e);
		}
		int loadCount = loader.load();
		try {
			loader.erase();
			scriptFileName = conf.getProperty("sql.fill.metadata");
			loader.getScript(scriptFileName);
		} catch (InvalidKeyException e) {
			logger.error(e.getMessage(), e);
		}
		loadCount += loader.load();
		assertEquals(25, loadCount);
	}
}