package model.entity;

import model.entity.enums.Rating;

public class Song extends Record {
	private String album;
	private Rating rating;

	public static class Builder {
		private String title = "";
		private long duration = 0;
		private String author = "";
		private String album = "";
		private Rating rating = Rating.UNRATED;

		public Builder() {
		}

		public Builder title(final String title) {
			this.title = title;
			return this;
		}

		public Builder author(final String author) {
			this.author = author;
			return this;
		}

		public Builder duration(final long duration) {
			this.duration = duration;
			return this;
		}

		public Builder album(final String album) {
			this.album = album;
			return this;
		}

		public Builder rating(final Rating rating) {
			this.rating = rating;
			return this;
		}

		public Song build() {
			return new Song(this);
		}
	}

	private Song(final Builder builder) {
		setTitle(builder.title);
		setAuthor(builder.author);
		setDuration(builder.duration);
		setAlbum(builder.album);
		setRating(builder.rating);
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(final String album) {
		this.album = album;
	}

	public int getRating() {
		return rating.getRating();
	}

	public void setRating(final Rating rating) {
		this.rating = rating;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		Song song = (Song) o;

		if (album != null ? !album.equals(song.album) : song.album != null) return false;
		return rating == song.rating;

	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (album != null ? album.hashCode() : 0);
		result = 31 * result + (rating != null ? rating.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return super.toString() +
				", album='" + album + '\'' +
				", rating=" + rating.toString();
	}
}
