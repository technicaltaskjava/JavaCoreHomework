package model.service.impl;

import model.entity.Record;
import model.entity.Song;
import model.entity.enums.Rating;
import model.service.MediaLibraryService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class MediaLibraryServiceImpl implements MediaLibraryService {
	private Song[] songs = null;

	@Override
	public Song[] getSongs() {
		return songs == null ? new Song[0] : Arrays.copyOf(songs, songs.length);
	}

	@Override
	public int update(final File file) throws FileNotFoundException {
		if (file == null) {
			return -1;
		}
		int countUpdate = 0;
		String[] songString = null;
		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNext()) {
				songString = songString == null ? new String[1] : Arrays.copyOf(songString, songString.length + 1);
				songString[songString.length - 1] = scanner.nextLine();
			}
		}
		if (songString == null) {
			return -1;
		}
		for (String s : songString) {
			String[] temp = s.split("\\|");
			if (temp.length != 5) {
				continue;
			}
			long duration = parseStringToLong(temp[2]);
			Rating rating = getRatingFromString(Integer.parseInt(temp[4]));
			Song song = new Song.Builder()
					.title(temp[0])
					.author(temp[1])
					.duration(duration)
					.album(temp[3])
					.rating(rating).build();
			songs = songs == null ? new Song[1] : Arrays.copyOf(songs, songs.length + 1);

			if (!findSongInLibrary(song)) {
				songs[songs.length - 1] = song;
				countUpdate++;
			}
		}

		return countUpdate;
	}

	private boolean findSongInLibrary(final Song song) {
		for (int index = 0; index < songs.length; index++) {
			return song.equals(songs[index]);
		}
		return false;
	}

	@Override
	public Record[] getRecordsByParam(final String title, final String author, final Long duration, final String album, final Rating rating) {
		Record[] resultSearch = null;

		for (int index = 0; index < songs.length; index++) {
			int hasTitle = 0;
			if (title != null) {
				hasTitle = songs[index].getTitle().equals(title) ? 1 : -1;
			}

			int hasAuthor = 0;
			if (author != null) {
				hasAuthor = songs[index].getAuthor().equals(author) ? 1 : -1;
			}

			int hasDuration = 0;
			if (duration != null) {
				hasDuration = songs[index].getDuration() == duration ? 1 : -1;
			}

			int hasAlbum = 0;
			if (album != null) {
				hasAuthor = songs[index].getAuthor().equals(album) ? 1 : -1;
			}

			int hasRating = 0;
			if (rating != null) {
				hasAuthor = songs[index].getRating() == rating.getRating() ? 1 : -1;
			}

			if (hasTitle >= 0 && hasAuthor >= 0 && hasDuration >= 0 && hasAlbum >= 0 && hasRating >= 0) {
				resultSearch = resizeRecords(resultSearch);
				resultSearch[resultSearch.length - 1] = songs[index];
			}
		}
		return resultSearch == null ? new Record[0] : resultSearch;
	}

	private Rating getRatingFromString(final int rating) {
		for (Rating r : Rating.values()) {
			if (rating == r.getRating()) {
				return r;
			}
		}
		return Rating.UNRATED;
	}

	private long parseStringToLong(final String time) {
		long duration = Long.parseLong(time.substring(0, 1)) * 3600;
		duration += Long.parseLong(time.substring(3, 5)) * 60;
		duration += Long.parseLong(time.substring(6, 8));
		return duration * 1000;
	}

	private Record[] resizeRecords(Record[] array) {
		if (array == null) {
			array = new Record[1];
		} else {
			array = Arrays.copyOf(array, array.length + 1);
		}
		return array;
	}
}
