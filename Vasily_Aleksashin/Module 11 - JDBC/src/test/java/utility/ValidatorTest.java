package utility;

import org.junit.Test;

public class ValidatorTest {
	@Test(expected = IllegalArgumentException.class)
	public void testIsNullWithMessage() {
		Validator.isNull(null, "Test message");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIsNull() {
		Validator.isNull(null);
	}
	
}