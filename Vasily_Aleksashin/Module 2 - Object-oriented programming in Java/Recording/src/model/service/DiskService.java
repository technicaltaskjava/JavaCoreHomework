package model.service;

import model.entity.Disk;
import model.entity.Record;

public interface DiskService {
	Disk getDisk();

	Record[] getRecordingQueue();

	boolean addRecordToQueue(final Record record);

	boolean burnDisk();

	String getRecordInfo(final Record record);

	String getDiskInfo();

	long getDiskMaxDuration();

	long getDiskCurrentDuration();

	long getDiskAvailableDuration();

	Record[] getRecordsByParam(final String title, final String author, final Long duration);

	Record[] sortByTitle(Record[] records);

	Record[] sortByAuthor(Record[] records);

	Record[] sortByDuration(Record[] records);
}
