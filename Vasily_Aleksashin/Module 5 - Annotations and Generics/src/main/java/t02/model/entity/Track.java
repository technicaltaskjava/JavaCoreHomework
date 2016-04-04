package t02.model.entity;

public class Track {
	private final String title;
	private final int duration;

	public Track(String title, int duration) {
		this.title = title;
		this.duration = duration;
	}

	public int getDuration() {
		return duration;
	}

	@Override
	public int hashCode() {
		int result = title.hashCode();
		result = 31 * result + duration;
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Track track = (Track) o;

		if (duration != track.duration) {
			return false;
		}
		return title.equals(track.title);

	}

	@Override
	public String toString() {
		return "Track{" +
				"title='" + title + '\'' +
				", duration=" + duration +
				'}';
	}
}
