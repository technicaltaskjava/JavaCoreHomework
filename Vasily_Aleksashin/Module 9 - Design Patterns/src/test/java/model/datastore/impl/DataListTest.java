package model.datastore.impl;

import model.UserForm;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class DataListTest {
	private static final String FIRST_NAME = "Jon";
	private static final String LAST_NAME = "Smith";
	private static final String USER_NAME = "login";
	private static final String EMAIL = "user@user.com";

	private DataList dataList;
	private UserForm userForm;

	@Before
	public void setUp() {
		dataList = new DataList();
		userForm = new UserForm.Builder(FIRST_NAME, LAST_NAME, USER_NAME).build();
		dataList.add(userForm);
	}

	@Test
	public void testGetByUserName() {
		assertEquals(userForm, dataList.getByUserName(userForm.getUserName()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetByUserNameNull() {
		dataList.getByUserName(null);
	}

	@Test
	public void testGetByUserNameNotExist() {
		assertNull(dataList.getByUserName("not exist"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUpdateNull() {
		dataList.update(null);
	}

	@Test
	public void testUpdate() {
		userForm = new UserForm.Builder(FIRST_NAME, LAST_NAME, USER_NAME).email(EMAIL).build();
		dataList.update(userForm);
		assertEquals(EMAIL, dataList.getByUserName(userForm.getUserName()).getEmail());
	}

	@Test
	public void testDelete() {
		dataList.delete(userForm.getUserName());
		assertNull(dataList.getByUserName(userForm.getUserName()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteNull() {
		dataList.delete(null);
	}

	@Test
	public void testGetAll() {
		final Collection<UserForm> userForms = dataList.getAll();
		assertFalse(userForms.isEmpty());
	}
}