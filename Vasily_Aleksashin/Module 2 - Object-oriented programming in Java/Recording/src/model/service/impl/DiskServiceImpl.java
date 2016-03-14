package model.service.impl;

import model.entity.Disk;
import model.entity.Record;
import model.service.DiskService;

import java.util.Arrays;
import java.util.Comparator;

public class DiskServiceImpl implements DiskService {
	private final Disk disk;
	private Record[] recordingQueue = null;
	private long currentDuration;

	public DiskServiceImpl(final String label, final int maxDiskTimeMinutes) {
		disk = new Disk(label, maxDiskTimeMinutes);
	}

	@Override
	public Disk getDisk() {
		return disk;
	}

	@Override
	public Record[] getRecordingQueue() {
		if (recordingQueue == null) {
			return new Record[0];
		}
		return Arrays.copyOf(recordingQueue, recordingQueue.length);
	}

	@Override
	public boolean addRecordToQueue(Record record) {
		if (record == null) {
			return false;
		}
		recordingQueue = resizeRecords(recordingQueue);
		recordingQueue[recordingQueue.length - 1] = record;
		currentDuration += record.getDuration();
		return true;
	}

	@Override
	public boolean burnDisk() {
		if (recordingQueue == null) {
			return false;
		}
		for (int index = 0; index < recordingQueue.length; index++) {
			boolean status = disk.addTrack(recordingQueue[index]);
			if (!status) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String getRecordInfo(Record record) {
		if (record == null) {
			return "No record available";
		}
		StringBuilder builder = new StringBuilder();
		builder.append("Record {");
		builder.append(record.toString());
		builder.append("}");
		return builder.toString();
	}

	@Override
	public String getDiskInfo() {
		return disk.toString();
	}

	@Override
	public long getDiskMaxDuration() {
		return disk.getMaxDuration();
	}

	@Override
	public long getDiskCurrentDuration() {
		return currentDuration;
	}

	@Override
	public long getDiskAvailableDuration() {
		return disk.getMaxDuration() - currentDuration;
	}

	@Override
	public Record[] getRecordsByParam(final String title, final String author, final Long duration) {
		Record[] target = disk.getTracks();
		Record[] resultSearch = null;

		for (int index = 0; index < target.length; index++) {
			int hasTitle = 0;
			if (title != null) {
				hasTitle = target[index].getTitle().equals(title) ? 1 : -1;
			}

			int hasAuthor = 0;
			if (author != null) {
				hasAuthor = target[index].getAuthor().equals(author) ? 1 : -1;
			}

			int hasDuration = 0;
			if (duration != null) {
				hasDuration = target[index].getDuration() == duration ? 1 : -1;
			}

			if (hasTitle >= 0 && hasAuthor >= 0 && hasDuration >= 0) {
				resultSearch = resizeRecords(resultSearch);
				resultSearch[resultSearch.length - 1] = target[index];
			}
		}
		return resultSearch == null ? new Record[0] : resultSearch;
	}

	@Override
	public Record[] sortByTitle(final Record[] records) {
		Arrays.sort(records, new Comparator<Record>() {
			@Override
			public int compare(final Record o1, final Record o2) {
				Integer result = ValidateCompareParameter(o1, o2);
				if (result != null) {
					return result;
				}
				return getCompareResult(o1.getTitle().compareTo(o2.getTitle()));
			}
		});
		return records;
	}

	@Override
	public Record[] sortByAuthor(final Record[] records) {
		Arrays.sort(records, new Comparator<Record>() {
			@Override
			public int compare(final Record o1, final Record o2) {
				Integer result = ValidateCompareParameter(o1, o2);
				if (result != null) {
					return result;
				}
				return getCompareResult(o1.getAuthor().compareTo(o2.getAuthor()));
			}
		});
		return records;
	}

	@Override
	public Record[] sortByDuration(final Record[] records) {
		Arrays.sort(records, new Comparator<Record>() {
			@Override
			public int compare(final Record o1, final Record o2) {
				Integer result = ValidateCompareParameter(o1, o2);
				if (result != null) {
					return result;
				}
				return getCompareResult((int) (o1.getDuration() - o2.getDuration()));
			}
		});
		return records;
	}

	private Record[] resizeRecords(Record[] array) {
		if (array == null) {
			array = new Record[1];
		} else {
			array = Arrays.copyOf(array, array.length + 1);
		}
		return array;
	}

	private Integer ValidateCompareParameter(final Record o1, final Record o2) {
		if (o1 == null && o2 == null) {
			return 0;
		}

		if (o1 == null) {
			return -1;
		}

		if (o2 == null) {
			return 1;
		}
		return null;
	}

	private int getCompareResult(final Integer result) {
		if (result > 0) {
			return 1;
		} else if (result < 0) {
			return -1;
		}
		return 0;
	}
}
