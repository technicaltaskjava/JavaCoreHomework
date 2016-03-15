package model.service;

import model.entity.Disk;
import model.entity.Record;
import model.service.impl.DiskServiceImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DiskServiceImplTest {
	private static final String LABEL = "My disk";
	private static final int DURATION = 80;
	private static final long MAX_DURATION_MILL = 80 * 60 * 1000;
	private DiskService diskService;
	private Record recordTest1 = new Record("test1", "test1", 3 * 60 * 1000);
	private Record recordTest2 = new Record("test2", "test2", 4 * 60 * 1000);
	private Record recordTest3 = new Record("test3", "test3", 5 * 60 * 1000);
	private Record recordTest4 = new Record("test4", "test4", 6 * 60 * 1000);

	@Before
	public void setUp() throws Exception {
		diskService = new DiskServiceImpl(LABEL, DURATION);
	}

	@Test
	public void testGetRecordingQueueEmpty() throws Exception {
		assertArrayEquals(new Record[0], diskService.getRecordingQueue());
	}

	@Test
	public void testGetRecordingQueue() throws Exception {
		diskService.addRecordToQueue(recordTest1);
		diskService.addRecordToQueue(recordTest2);
		diskService.addRecordToQueue(recordTest3);
		diskService.addRecordToQueue(recordTest4);
		Record[] expected = new Record[]{recordTest1, recordTest2, recordTest3, recordTest4};
		assertArrayEquals(expected, diskService.getRecordingQueue());
	}

	@Test
	public void testAddRecordToQueue() throws Exception {
		assertTrue(diskService.addRecordToQueue(new Record()));
	}

	@Test
	public void testAddRecordToQueueWithNull() throws Exception {
		assertFalse(diskService.addRecordToQueue(null));
	}

	@Test
	public void testBurnDisk() throws Exception {
		diskService.addRecordToQueue(recordTest1);
		diskService.addRecordToQueue(recordTest2);
		diskService.addRecordToQueue(recordTest3);
		diskService.addRecordToQueue(recordTest4);
		assertTrue(diskService.burnDisk());
	}

	@Test
	public void testBurnDiskIsEmpty() throws Exception {
		assertFalse(diskService.burnDisk());
	}

	@Test
	public void testGetRecordInfoWithNull() throws Exception {
		String expected = "No record available";
		String actual = diskService.getRecordInfo(null);
		assertEquals(expected, actual);
	}

	@Test
	public void testGetRecordInfo() throws Exception {
		String expected = "Record {title='test1', author='test1', duration=00:03:00}";
		String actual = diskService.getRecordInfo(recordTest1);
		assertEquals(expected, actual);
	}

	@Test
	public void testGetDiskInfo() throws Exception {
		diskService.addRecordToQueue(recordTest1);
		diskService.burnDisk();
		Disk disk = new Disk(LABEL, DURATION);
		disk.addTrack(recordTest1);
		assertEquals(disk.toString(), diskService.getDiskInfo());
	}

	@Test
	public void testGetRecordByTitle() throws Exception {
		recordTest1 = new Record("test1", "test2", 4 * 60 * 1000);
		recordTest2 = new Record("test1", "test3", 3 * 60 * 1000);
		recordTest3 = new Record("test3", "test3", 5 * 60 * 1000);
		recordTest4 = new Record("test3", "test4", 5 * 60 * 1000);
		diskService.addRecordToQueue(recordTest1);
		diskService.addRecordToQueue(recordTest2);
		diskService.addRecordToQueue(recordTest3);
		diskService.addRecordToQueue(recordTest4);
		diskService.burnDisk();
		Record[] expected = new Record[]{recordTest1, recordTest2};
		Record[] actual = diskService.getRecordsByParam("test1", null, null);
		assertTrue(expected.length == actual.length);
	}

	@Test
	public void testGetRecordByAuthor() throws Exception {
		recordTest1 = new Record("test1", "test2", 4 * 60 * 1000);
		recordTest2 = new Record("test1", "test3", 3 * 60 * 1000);
		recordTest3 = new Record("test3", "test3", 5 * 60 * 1000);
		recordTest4 = new Record("test3", "test2", 5 * 60 * 1000);
		diskService.addRecordToQueue(recordTest1);
		diskService.addRecordToQueue(recordTest2);
		diskService.addRecordToQueue(recordTest3);
		diskService.addRecordToQueue(recordTest4);
		diskService.burnDisk();
		Record[] expected = new Record[]{recordTest2, recordTest3};
		Record[] actual = diskService.getRecordsByParam(null, "test3", null);
		assertTrue(expected.length == actual.length);
	}

	@Test
	public void testGetRecordByDuration() throws Exception {
		recordTest1 = new Record("test1", "test2", 4 * 60 * 1000);
		recordTest2 = new Record("test1", "test3", 4 * 60 * 1000);
		recordTest3 = new Record("test3", "test3", 5 * 60 * 1000);
		recordTest4 = new Record("test3", "test2", 5 * 60 * 1000);
		diskService.addRecordToQueue(recordTest1);
		diskService.addRecordToQueue(recordTest2);
		diskService.addRecordToQueue(recordTest3);
		diskService.addRecordToQueue(recordTest4);
		diskService.burnDisk();
		Record[] expected = new Record[]{recordTest3, recordTest4};
		Record[] actual = diskService.getRecordsByParam(null, null, (long) (5 * 60 * 1000));
		assertTrue(expected.length == actual.length);
	}

	@Test
	public void testGetRecordByTitleAndAuthor() throws Exception {
		recordTest1 = new Record("test1", "test2", 4 * 60 * 1000);
		recordTest2 = new Record("test1", "test3", 3 * 60 * 1000);
		recordTest3 = new Record("test3", "test3", 5 * 60 * 1000);
		recordTest4 = new Record("test3", "test4", 5 * 60 * 1000);
		diskService.addRecordToQueue(recordTest1);
		diskService.addRecordToQueue(recordTest2);
		diskService.addRecordToQueue(recordTest3);
		diskService.addRecordToQueue(recordTest4);
		diskService.burnDisk();
		Record[] expected = new Record[]{recordTest2};
		Record[] actual = diskService.getRecordsByParam("test1", "test3", null);
		assertTrue(expected.length == actual.length);
	}

	@Test
	public void testGetRecordByTitleAndDuration() throws Exception {
		recordTest1 = new Record("test3", "test2", 5 * 60 * 1000);
		recordTest2 = new Record("test1", "test3", 3 * 60 * 1000);
		recordTest3 = new Record("test3", "test3", 5 * 60 * 1000);
		recordTest4 = new Record("test3", "test4", 6 * 60 * 1000);
		diskService.addRecordToQueue(recordTest1);
		diskService.addRecordToQueue(recordTest2);
		diskService.addRecordToQueue(recordTest3);
		diskService.addRecordToQueue(recordTest4);
		diskService.burnDisk();
		Record[] expected = new Record[]{recordTest1, recordTest3};
		Record[] actual = diskService.getRecordsByParam("test3", null, (long) (5 * 60 * 1000));
		assertTrue(expected.length == actual.length);
	}

	@Test
	public void testGetRecordByAuthorAndDuration() throws Exception {
		recordTest1 = new Record("test3", "test3", 5 * 60 * 1000);
		recordTest2 = new Record("test1", "test3", 3 * 60 * 1000);
		recordTest3 = new Record("test3", "test3", 5 * 60 * 1000);
		recordTest4 = new Record("test3", "test4", 6 * 60 * 1000);
		diskService.addRecordToQueue(recordTest1);
		diskService.addRecordToQueue(recordTest2);
		diskService.addRecordToQueue(recordTest3);
		diskService.addRecordToQueue(recordTest4);
		diskService.burnDisk();
		Record[] expected = new Record[]{recordTest1, recordTest3};
		Record[] actual = diskService.getRecordsByParam(null, "test3", (long) (5 * 60 * 1000));
		assertTrue(expected.length == actual.length);
	}

	@Test
	public void testGetRecordByAll() throws Exception {
		recordTest1 = new Record("test3", "test3", 5 * 60 * 1000);
		recordTest2 = new Record("test1", "test3", 5 * 60 * 1000);
		recordTest3 = new Record("test3", "test3", 5 * 60 * 1000);
		recordTest4 = new Record("test3", "test4", 5 * 60 * 1000);
		diskService.addRecordToQueue(recordTest1);
		diskService.addRecordToQueue(recordTest2);
		diskService.addRecordToQueue(recordTest3);
		diskService.addRecordToQueue(recordTest4);
		diskService.burnDisk();
		Record[] expected = new Record[]{recordTest1, recordTest3};
		Record[] actual = diskService.getRecordsByParam("test3", "test3", (long) (5 * 60 * 1000));
		assertTrue(expected.length == actual.length);
	}

	@Test
	public void testSortQueueByTitle() throws Exception {
		recordTest1 = new Record("test1", "test2", 4 * 60 * 1000);
		recordTest2 = new Record("test1", "test1", 3 * 60 * 1000);
		recordTest3 = new Record("test3", "test3", 5 * 60 * 1000);
		diskService.addRecordToQueue(recordTest3);
		diskService.addRecordToQueue(recordTest1);
		diskService.addRecordToQueue(recordTest2);
		Record[] expected = new Record[]{recordTest1, recordTest2, recordTest3};
		Record[] actual = diskService.sortByTitle(diskService.getRecordingQueue());
		assertArrayEquals(expected, actual);
	}

	@Test
	public void testSortQueueByAuthor() throws Exception {
		recordTest1 = new Record("test2", "test1", 4 * 60 * 1000);
		recordTest2 = new Record("test1", "test1", 3 * 60 * 1000);
		recordTest3 = new Record("test3", "test3", 5 * 60 * 1000);
		diskService.addRecordToQueue(recordTest3);
		diskService.addRecordToQueue(recordTest1);
		diskService.addRecordToQueue(recordTest2);
		Record[] expected = new Record[]{recordTest1, recordTest2, recordTest3};
		Record[] actual = diskService.sortByAuthor(diskService.getRecordingQueue());
		assertArrayEquals(expected, actual);
	}

	@Test
	public void testSortQueueByDuration() throws Exception {
		recordTest1 = new Record("test2", "test2", 4 * 60 * 1000);
		recordTest2 = new Record("test1", "test1", 5 * 60 * 1000);
		recordTest3 = new Record("test3", "test3", 5 * 60 * 1000);
		diskService.addRecordToQueue(recordTest3);
		diskService.addRecordToQueue(recordTest1);
		diskService.addRecordToQueue(recordTest2);
		Record[] expected = new Record[]{recordTest1, recordTest3, recordTest2};
		Record[] actual = diskService.sortByDuration(diskService.getRecordingQueue());
		assertArrayEquals(expected, actual);
	}

	@Test
	public void testGetDiskMaxDuration() {
		assertTrue(diskService.getDiskMaxDuration() == MAX_DURATION_MILL);
	}

	@Test
	public void testGetDiskCurrentDuration() {
		diskService.addRecordToQueue(recordTest1);
		diskService.burnDisk();
		assertTrue(diskService.getDiskCurrentDuration() == recordTest1.getDuration());
	}

	@Test
	public void testGetDiskAvailableDuration() {
		diskService.addRecordToQueue(recordTest1);
		diskService.burnDisk();
		assertTrue(diskService.getDiskAvailableDuration() == MAX_DURATION_MILL - recordTest1.getDuration());
	}

	@Test
	public void testGetDisk() {
		assertNotNull(diskService.getDisk());
	}

	@Test
	public void testSortDiskByTitle() throws Exception {
		recordTest1 = new Record("test1", "test2", 4 * 60 * 1000);
		recordTest2 = new Record("test1", "test1", 3 * 60 * 1000);
		recordTest3 = new Record("test3", "test3", 5 * 60 * 1000);
		diskService.addRecordToQueue(recordTest3);
		diskService.addRecordToQueue(recordTest1);
		diskService.addRecordToQueue(recordTest2);
		diskService.burnDisk();
		String[] expected = new String[]{recordTest1.getTitle(), recordTest2.getTitle(), recordTest3.getTitle()};
		Record[] sortedDiskRecords = diskService.sortByTitle(diskService.getDisk().getTracks());
		String[] actual = new String[3];
		for (int index = 0; index < sortedDiskRecords.length; index++) {
			actual[index] = sortedDiskRecords[index].getTitle();
		}
		assertArrayEquals(expected, actual);
	}

	@Test
	public void testSortDiskByAuthor() throws Exception {
		recordTest1 = new Record("test1", "test2", 4 * 60 * 1000);
		recordTest2 = new Record("test1", "test1", 3 * 60 * 1000);
		recordTest3 = new Record("test3", "test3", 5 * 60 * 1000);
		diskService.addRecordToQueue(recordTest3);
		diskService.addRecordToQueue(recordTest1);
		diskService.addRecordToQueue(recordTest2);
		diskService.burnDisk();
		String[] expected = new String[]{recordTest2.getAuthor(), recordTest1.getAuthor(), recordTest3.getAuthor()};
		Record[] sortedDiskRecords = diskService.sortByAuthor(diskService.getDisk().getTracks());
		String[] actual = new String[3];
		for (int index = 0; index < sortedDiskRecords.length; index++) {
			actual[index] = sortedDiskRecords[index].getAuthor();
		}
		assertArrayEquals(expected, actual);
	}

	@Test
	public void testSortDiskByDuration() throws Exception {
		recordTest1 = new Record("test1", "test2", 4 * 60 * 1000);
		recordTest2 = new Record("test1", "test1", 3 * 60 * 1000);
		recordTest3 = new Record("test3", "test3", 5 * 60 * 1000);
		diskService.addRecordToQueue(recordTest3);
		diskService.addRecordToQueue(recordTest1);
		diskService.addRecordToQueue(recordTest2);
		diskService.burnDisk();
		long[] expected = new long[]{recordTest2.getDuration(), recordTest1.getDuration(), recordTest3.getDuration()};
		Record[] sortedDiskRecords = diskService.sortByDuration(diskService.getDisk().getTracks());
		long[] actual = new long[3];
		for (int index = 0; index < sortedDiskRecords.length; index++) {
			actual[index] = sortedDiskRecords[index].getDuration();
		}
		assertArrayEquals(expected, actual);
	}
}