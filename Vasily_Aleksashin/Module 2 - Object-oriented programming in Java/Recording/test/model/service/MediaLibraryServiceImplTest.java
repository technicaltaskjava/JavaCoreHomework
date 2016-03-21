package model.service;

import model.entity.Record;
import model.entity.Song;
import model.entity.enums.Rating;
import model.service.impl.MediaLibraryServiceImpl;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class MediaLibraryServiceImplTest {

	@Test
	public void testUpdate() throws Exception {
		File file = new File(getClass().getClassLoader().getResource("libraryTest.txt").getFile().replaceAll("%20", " "));
		MediaLibraryService libraryService = new MediaLibraryServiceImpl();
		assertTrue(libraryService.update(file) == 9);
	}

	@Test (expected = FileNotFoundException.class)
	public void testUpdateWithNoFile() throws Exception {
		MediaLibraryService libraryService = new MediaLibraryServiceImpl();
		libraryService.update(new File(""));
	}

	@Test
	public void testUpdateWithNull() throws Exception {
		MediaLibraryService libraryService = new MediaLibraryServiceImpl();
		assertTrue(libraryService.update(null) == -1);
	}

	@Test
	public void testGetSongs() throws FileNotFoundException {
		File file = new File(getClass().getClassLoader().getResource("libraryTest.txt").getFile().replaceAll("%20", " "));
		MediaLibraryService libraryService = new MediaLibraryServiceImpl();
		libraryService.update(file);
		assertTrue(libraryService.getSongs().length == 9);
	}

	@Test
	public void testGetSongsNull() throws FileNotFoundException {
		MediaLibraryService libraryService = new MediaLibraryServiceImpl();
		Song[] expected = new Song[0];
		assertArrayEquals(expected, libraryService.getSongs());
	}

	@Test
	public void testGetRecordsByRating() throws FileNotFoundException {
		MediaLibraryService libraryService = new MediaLibraryServiceImpl();
		File file = new File(getClass().getClassLoader().getResource("libraryTest.txt").getFile().replaceAll("%20", " "));
		libraryService.update(file);
		Record[] expected = libraryService.getRecordsByParam(null, null, null, null, Rating.THREE);
		assertTrue(expected.length == 2);
	}

}