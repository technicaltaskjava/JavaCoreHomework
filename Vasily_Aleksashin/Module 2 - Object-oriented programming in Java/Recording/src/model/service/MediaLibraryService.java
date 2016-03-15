package model.service;

import model.entity.Record;
import model.entity.Song;
import model.entity.enums.Rating;

import java.io.File;
import java.io.FileNotFoundException;

public interface MediaLibraryService {
	Song[] getSongs();

	int update(final File file) throws FileNotFoundException;

	Record[] getRecordsByParam(final String title, final String author, final Long duration, final String album, final Rating rating);
}
