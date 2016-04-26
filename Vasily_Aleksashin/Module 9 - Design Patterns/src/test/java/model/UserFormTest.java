package model;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserFormTest {
	private static final String FIRST_NAME = "Jon";
	private static final String LAST_NAME = "Smith";
	private static final String USER_NAME = "login";
	private static final String EMAIL = "email@email.com";
	private static final String ADDRESS = "1985, Wall str.";
	private static final String LANGUAGE = "English";

	private static UserForm userForm;

	@BeforeClass
	public static void setUp() {
		userForm = new UserForm.Builder(FIRST_NAME, LAST_NAME, USER_NAME)
				.email(EMAIL)
				.address(ADDRESS)
				.language(LANGUAGE)
				.build();
	}

	@Test
	public void testGetFirstName() {
		assertEquals(FIRST_NAME, userForm.getFirstName());
	}

	@Test
	public void testGetLastName() {
		assertEquals(LAST_NAME, userForm.getLastName());
	}

	@Test
	public void testGetUserName() {
		assertEquals(USER_NAME, userForm.getUserName());
	}

	@Test
	public void testGetEmail() {
		assertEquals(EMAIL, userForm.getEmail());
	}

	@Test
	public void testGetAddress() {
		assertEquals(ADDRESS, userForm.getAddress());
	}

	@Test
	public void testGetLanguage() {
		assertEquals(LANGUAGE, userForm.getLanguage());
	}

	@Test
	public void testToString() {
		final String expected = "UserForm{\n" +
				"first name='Jon',\n" +
				"last name='Smith',\n" +
				"user name='login',\n" +
				"email='email@email.com',\n" +
				"address='1985, Wall str.',\n" +
				"language='English'\n" +
				"}";
		assertEquals(expected, userForm.toString());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIsNull() {
		new UserForm.Builder(null, null, null).build();
	}
}