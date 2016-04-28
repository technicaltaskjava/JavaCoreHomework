package model.datastore.impl;

import model.UserForm;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class DataSetTest {
	private static final String FIRST_NAME = "Jon";
	private static final String LAST_NAME = "Smith";
	private static final String USER_NAME = "login";
	private static final String EMAIL = "user@user.com";
	
	private DataSet dataSet;
	private UserForm userForm;
	
	@Before
	public void setUp() {
		dataSet = new DataSet();
		userForm = new UserForm.Builder(FIRST_NAME, LAST_NAME, USER_NAME).build();
		dataSet.add(userForm);
	}

	@Test
	public void testGetByUserName() {
		assertEquals(userForm, dataSet.getByUserName(userForm.getUserName()));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetByUserNameNull() {
		dataSet.getByUserName(null);
	}
	
	@Test
	public void testGetByUserNameNotExist() {
		assertNull(dataSet.getByUserName("not exist"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testUpdateNull() {
		dataSet.update(null);
	}
	
	@Test
	public void testUpdate() {
		userForm = new UserForm.Builder(FIRST_NAME, LAST_NAME, USER_NAME).email(EMAIL).build();
		dataSet.update(userForm);
		assertEquals(EMAIL, dataSet.getByUserName(userForm.getUserName()).getEmail());
	}
	
	@Test
	public void testDelete() {
		dataSet.delete(userForm.getUserName());
		assertNull(dataSet.getByUserName(userForm.getUserName()));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDeleteNull() {
		dataSet.delete(null);
	}

	@Test
	public void testGetAll() {
		final Collection<UserForm> userForms = dataSet.getAll();
		assertFalse(userForms.isEmpty());
	}
}