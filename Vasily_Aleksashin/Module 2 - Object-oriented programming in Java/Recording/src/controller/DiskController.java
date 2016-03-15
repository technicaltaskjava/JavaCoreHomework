package controller;

import model.entity.Record;
import model.service.DiskService;
import model.service.impl.DiskServiceImpl;

public class DiskController {
	private final DiskService service;
	private boolean diskFinalized = false;

	public DiskController(final String diskLabel, final int diskMaxDurationMinute) throws IllegalArgumentException {
		if (diskLabel == null) {
			throw new IllegalArgumentException("Disk label can not be NULL");
		}
		if (diskMaxDurationMinute <= 0) {
			throw new IllegalArgumentException("Disk duration must be more then ZERO");
		}
		service = new DiskServiceImpl(diskLabel, diskMaxDurationMinute);
	}

	public boolean isDiskFinalized() {
		return diskFinalized;
	}

	public boolean addSongToDisk(final Record record) throws IllegalArgumentException {
		if (record == null) {
			throw new IllegalArgumentException("Record can not be NULL");
		}
		return service.addRecordToQueue(record);
	}

	public void burnSongsToDisk() {
		if (!diskFinalized) {
			diskFinalized = service.burnDisk();
		}
	}

	public long getDiskAvailableDuration() {
		return service.getDiskAvailableDuration();
	}

	public long getDiskCurrentDuration() {
		return service.getDiskCurrentDuration();
	}

	public long getDiskMaxDuration() {
		return service.getDiskMaxDuration();
	}

	public Record[] getDiskRecords() {
		return service.getDisk().getTracks();
	}

	public Record[] getRecordsForBurning() {
		return service.getRecordingQueue();
	}

	public Record[] findRecords(final String title, String author, Long duration) {
		return service.getRecordsByParam(title, author, duration);
	}

	public Record[] sortDiskTrackByTitle() {
		return service.sortByTitle(service.getDisk().getTracks());
	}

	public Record[] sortDiskTrackByAuthor() {
		return service.sortByAuthor(service.getDisk().getTracks());
	}

	public Record[] sortDiskTrackByDuration() {
		return service.sortByDuration(service.getDisk().getTracks());
	}
}
