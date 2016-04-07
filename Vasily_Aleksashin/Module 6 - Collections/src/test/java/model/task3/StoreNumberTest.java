package model.task3;

import exception.ParameterValidateException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StoreNumberTest {
	private StoreNumber store;

	@Before
	public void setUp() {
		store = new StoreNumber();
	}

	@Test
	public void testAdd() throws ParameterValidateException {
		store.add(4.0);
		assertTrue(store.getStore().contains(4.0));
	}

	@Test
	public void testRemove() throws ParameterValidateException {
		store.add(4.0);
		store.add(2.4);
		store.remove(4.0);
		assertFalse(store.getStore().contains(4.0));
	}

	@Test
	public void testFind() throws ParameterValidateException {
		store.add(4.5);
		store.add(6.1);
		store.add(2.5);
		store.add(9.7);
		assertEquals(4.5, store.find(4.0), 0);
	}

	@Test
	public void testGetStore() throws ParameterValidateException {
		List<Double> expected = new ArrayList<>();
		expected.add(1.1);
		store.add(1.1);
		assertEquals(expected, store.getStore());
	}

	@Test(expected = ParameterValidateException.class)
	public void testAddNull() throws ParameterValidateException {
		store.add(null);
	}

	@Test(expected = ParameterValidateException.class)
	public void testRemoveNull() throws ParameterValidateException {
		store.remove(null);
	}

	@Test(expected = ParameterValidateException.class)
	public void testFindNull() throws ParameterValidateException {
		store.find(null);
	}
}