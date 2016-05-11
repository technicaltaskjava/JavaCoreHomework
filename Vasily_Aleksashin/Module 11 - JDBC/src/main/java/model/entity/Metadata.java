package model.entity;

import utility.Validator;

import java.sql.Timestamp;

public class Metadata implements Identified {
	private Integer id;
	private Cookie cookie;
	private User user;
	private Timestamp timeAdded;

	public Cookie getCookie() {
		return cookie;
	}

	public void setCookie(Cookie cookie) {
		this.cookie = cookie;
	}

	@Override
	public Integer getId() {
		return id;
	}

	protected void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getTimeAdded() {
		return timeAdded;
	}

	public void setTimeAdded(Timestamp timeAdded) {
		Validator.isNull(timeAdded, "Time added");
		this.timeAdded = timeAdded;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		Validator.isNull(user, "User name");
		this.user = user;
	}

	@Override
	public int hashCode() {
		int result = cookie.hashCode();
		result = 31 * result + user.hashCode();
		result = 31 * result + timeAdded.hashCode();
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
		Metadata metadata = (Metadata) o;
		return cookie.equals(metadata.cookie) && user.equals(metadata.user) && timeAdded.equals(metadata.timeAdded);

	}

	@Override
	public String toString() {
		return "Metadata{id=" + id + ", timeAdded=" + timeAdded +
				"\n\tuser=" + user +
				"\n\tcookie=" + cookie +
				"\n}";
	}
}
