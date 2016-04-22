package model.task2;

import exception.FileIOException;
import exception.ParameterIncorrectException;
import org.junit.Before;
import org.junit.Test;
import utility.Constant;

import static org.junit.Assert.assertTrue;

public class StatementLoaderTest {
	private static final String PATH = "src/test/resources/test_statement.txt";

	private TransactionService service;
	private StatementLoader loader;

	@Before
	public void setUp() throws ParameterIncorrectException {
		service = new TransactionService(Constant.DEFAULT_ACCOUNT_COUNT);
		loader = new StatementLoader(service);
	}

	@Test
	public void testLoad() throws FileIOException {
		loader.load(PATH);
		assertTrue(service.getRecords().size() == 5);
	}

	@Test(expected = FileIOException.class)
	public void testLoadNull() throws FileIOException {
		loader.load(null);
	}
}