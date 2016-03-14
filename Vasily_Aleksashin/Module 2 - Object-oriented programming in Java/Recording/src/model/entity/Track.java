package model.entity;

public class Track extends Record {
	private int id;

	public Track(final int id) {
		this.id = id;
	}

	public Track(final Record record, final int id) {
		this(id);
		this.title = record.getTitle();
		this.author = record.getAuthor();
		this.duration = record.getDuration();
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		Track track = (Track) o;

		return id == track.id;

	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + id;
		return result;
	}

	@Override
	public String toString() {
		return "Track{" +
				"id=" + id + ", " +
				super.toString() +
				'}';
	}
}
