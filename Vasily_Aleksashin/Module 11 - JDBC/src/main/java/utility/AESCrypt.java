package utility;

import exception.CryptException;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;

public class AESCrypt {
	private AESCrypt() {
	}

	public static String encrypt(String value) throws CryptException {
		try {
			byte[] message = value.getBytes("UTF-8");
			return DatatypeConverter.printBase64Binary(message);
		} catch (UnsupportedEncodingException e) {
			throw new CryptException(e.getMessage(), e);
		}
	}

	public static String decrypt(String value) throws CryptException {
		try {
			return new String(DatatypeConverter.parseBase64Binary(value), "utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new CryptException(e.getMessage(), e);
		}
	}
}
