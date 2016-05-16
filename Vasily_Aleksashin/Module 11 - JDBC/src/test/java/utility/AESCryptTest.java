package utility;

import exception.CryptException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AESCryptTest {
	private static final String PASS = "root";
	private static final String ENCRYPT_PASS = "cm9vdA==";

	@Test
	public void testEncrypt() throws CryptException {
		final String actual = AESCrypt.encrypt(PASS);
		assertEquals(ENCRYPT_PASS, actual);
	}

	@Test
	public void testDecrypt() throws CryptException {
		final String actual = AESCrypt.decrypt(ENCRYPT_PASS);
		assertEquals(PASS, actual);
	}
	
}