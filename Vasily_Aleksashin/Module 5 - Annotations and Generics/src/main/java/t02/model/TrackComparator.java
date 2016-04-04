package t02.model;

import t02.model.entity.Track;

import java.util.Comparator;

public class TrackComparator implements Comparator<Track> {
	@Override
	public int compare(Track track1, Track track2) {
		if (track1 == null && track2 == null) {
			return 0;
		}
		if (track1 == null) {
			return -1;
		}
		if (track2 == null) {
			return 1;
		}
		if (track1.equals(track2)) {
			return 0;
		}
		return track1.getDuration() - track2.getDuration();
	}
}
