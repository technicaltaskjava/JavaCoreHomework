package t02.model;

import t02.model.entity.Track;
import t02.model.exception.FileIOException;
import t02.model.exception.ParameterIsNullException;
import t02.util.Utility;

import java.util.Arrays;

public class TrackService {
	private Track[] tracks = null;

	public Track[] getTracks(final String fileName) throws FileIOException, ParameterIsNullException {
		String[][] inputData = Utility.load(fileName);
		for (String[] strings : inputData) {
			resizeTracks();
			String title = strings[0];
			int duration = Utility.stringTimeToInt(strings[1]);
			Track track = new Track(title, duration);
			tracks[tracks.length - 1] = track;
		}
		return tracks;
	}

	private void resizeTracks() {
		if (tracks == null) {
			tracks = new Track[1];
		} else {
			tracks = Arrays.copyOf(tracks, tracks.length + 1);
		}
	}
}
