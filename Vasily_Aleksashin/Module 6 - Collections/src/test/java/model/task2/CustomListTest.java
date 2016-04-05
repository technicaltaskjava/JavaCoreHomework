package model.task2;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static org.junit.Assert.*;

public class CustomListTest {
	private static final String TEST = "test";
	private CustomList<String> list;
	private ArrayList<String> arrayList;

	@Before
	public void setUp() {
		list = new CustomList<>();
		arrayList = new ArrayList<>();
	}

	@Test
	public void testSize() {
		assertTrue(list.size() == 0);
	}

	@Test
	public void testIsEmpty() {
		assertTrue(list.isEmpty());
	}

	@Test
	public void testContains() {
		list.add(TEST);
		assertTrue(list.contains(TEST));
	}

	@Test
	public void testIterator() {
		Iterator<String> iterator = list.iterator();
		list.add(TEST);
		assertTrue(iterator.hasNext());
	}

	@Test
	public void testToArray() {
		list.add(TEST);
		Object[] objects = new Object[]{TEST};
		assertArrayEquals(objects, list.toArray());
	}

	@Test
	public void testToArrayCollection() {
		list.add(TEST);
		Object[] expected = new Object[]{TEST};
		Object[] actual = new Object[0];
		assertArrayEquals(expected, list.toArray(actual));
	}

	@Test
	public void testAdd() {
		assertTrue(list.add(TEST));
	}

	@Test
	public void testRemove() {
		list.add(TEST);
		assertTrue(list.remove(TEST));
	}

	@Test
	public void testContainsAll() {
		list.add(TEST);

		arrayList.add(TEST);
		assertTrue(list.containsAll(arrayList));
	}

	@Test
	public void testAddAll() {
		arrayList.add(TEST);
		arrayList.add(TEST);
		arrayList.add("test1");
		list.addAll(arrayList);
		assertTrue(list.containsAll(arrayList));
	}

	@Test
	public void testAddAllIndex() {
		list.add(TEST);
		arrayList.add(TEST);
		arrayList.add(TEST);
		list.addAll(0, arrayList);
		assertTrue(list.size() == 3);
	}

	@Test
	public void testRemoveAll() {
		arrayList.add(TEST);
		list.add(TEST);
		list.removeAll(arrayList);
		assertFalse(list.containsAll(arrayList));
	}

	@Test
	public void testRetainAll() {
		arrayList.add(TEST);
		list.add(TEST);
		list.add("test1");
		list.retainAll(arrayList);
		assertFalse(list.contains("test1"));
	}

	@Test
	public void testClear() {
		list.add(TEST);
		list.clear();
		assertTrue(list.isEmpty());
	}

	@Test
	public void testGet() {
		list.add(TEST);
		assertEquals(TEST, list.get(0));
	}

	@Test
	public void testSet() {
		list.add(TEST);
		list.set(0, "test1");
		assertFalse(list.contains(TEST));
	}

	@Test
	public void testAddIndex() {
		list.add(TEST);

		list.add(0, "test1");
		assertTrue(list.contains("test1"));
	}

	@Test
	public void testRemoveIndex() {
		list.add(TEST);
		list.add("test1");
		list.remove(0);
		assertFalse(list.contains(TEST));
	}

	@Test
	public void testIndexOf() {
		list.add(TEST);
		list.add("test1");
		list.add(TEST);
		assertTrue(list.indexOf(TEST) == 0);
	}

	@Test
	public void testLastIndexOf() {
		list.add(TEST);
		list.add("test1");
		list.add(TEST);
		assertTrue(list.lastIndexOf(TEST) == 2);
	}

	@Test
	public void testListIterator() {
		ListIterator<String> listIterator = list.listIterator();
		list.add(TEST);
		assertTrue(listIterator.nextIndex() == 0);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testListIteratorIndex() {
		ListIterator<String> listIterator = list.listIterator(1);
		list.add(TEST);

		listIterator.nextIndex();
	}

	@Test
	public void testSubList() {
		list.add(TEST);
		list.add(TEST);
		list.add(TEST);
		List<String> subList = list.subList(1, 2);
		assertTrue(subList.size() == 1);
	}
	
}