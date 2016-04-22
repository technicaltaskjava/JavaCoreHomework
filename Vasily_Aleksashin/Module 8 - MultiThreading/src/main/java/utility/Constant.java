package utility;

import java.util.regex.Pattern;

public class Constant {
	public static final String SEPARATOR = "==========================================================";
	public static final String RESULT = "The duration of the search prime number with adding primes in general collection";
	public static final String ITEM = "Find prime number with adding primes in general collection";
	public static final String STATEMENT_FILE = "src/main/resources/statement.txt";
	public static final int SENDER_ID = 1;
	public static final int RECIPIENT_ID = 2;
	public static final int SUM = 100;
	public static final int DEFAULT_ACCOUNT_COUNT = 5;
	public static final int ZERO_BALANCE = 0;
	public static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
	public static final Pattern DELIMITER = Pattern.compile("(;)");

	private Constant() {
	}
}
