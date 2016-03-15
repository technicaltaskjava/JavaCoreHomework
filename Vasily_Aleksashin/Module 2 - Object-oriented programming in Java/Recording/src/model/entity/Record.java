package model.entity;

public class Record {
	protected String title = "";
	protected String author = "";
	protected long duration = 0;

	public Record(final String title, final String author, final long duration) {
		this.title = title;
		this.author = author;
		this.duration = duration;
	}

	public Record() {
	}

	public String getTitle() {
		return title;
	}

	public long getDuration() {
		return duration;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(final String author) {
		this.author = author;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public void setDuration(final long duration) {
		this.duration = duration;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Record record = (Record) o;

		if (duration != record.duration) return false;
		if (title != null ? !title.equals(record.title) : record.title != null) return false;
		return author != null ? author.equals(record.author) : record.author == null;

	}

	@Override
	public int hashCode() {
		int result = title != null ? title.hashCode() : 0;
		result = 31 * result + (author != null ? author.hashCode() : 0);
		result = 31 * result + (int) duration;
		return result;
	}

	@Override
	public String toString() {
		long secs = duration / 1000;
		String formatDuration = String.format("%02d:%02d:%02d", secs / 3600, (secs % 3600) / 60, (secs % 60));
		return "title='" + title + '\'' + ", author='" + author + '\'' + ", duration=" + formatDuration;
	}
}
