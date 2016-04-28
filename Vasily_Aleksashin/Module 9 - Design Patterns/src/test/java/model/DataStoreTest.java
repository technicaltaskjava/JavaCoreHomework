package model;

import model.datastore.DataFactory;
import model.datastore.UserData;
import model.datastore.impl.DataList;
import model.datastore.impl.DataSet;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class DataStoreTest {
	private DataFactory factory;

	@Before
	public void setUp() {
		factory = new DataStore();
	}

	@Test
	public void testGetListData() {
		final UserData listData = factory.getListData();
		assertTrue(listData instanceof DataList);
	}

	@Test
	public void testGetSetData() {
		final UserData setData = factory.getSetData();
		assertTrue(setData instanceof DataSet);
	}
}