package model.entity;

import utility.Validator;

public class Cookie implements Identified {
	private Integer id;
	private String cookieMessage;
	private int luckyNumber;

	@Override
	public Integer getId() {
		return id;
	}

	protected void setId(Integer id) {
		this.id = id;
	}

	public String getCookieMessage() {
		return cookieMessage;
	}

	public void setCookieMessage(String cookie) {
		Validator.isNull(cookie);
		cookieMessage = cookie;
	}

	public int getLuckyNumber() {
		return luckyNumber;
	}

	public void setLuckyNumber(int luckyNumber) {
		numberCheck(luckyNumber);
		this.luckyNumber = luckyNumber;
	}

	@Override
	public int hashCode() {
		int result = cookieMessage.hashCode();
		result = 31 * result + luckyNumber;
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
		Cookie cookie1 = (Cookie) o;
		return luckyNumber == cookie1.luckyNumber && cookieMessage.equals(cookie1.cookieMessage);
	}

	@Override
	public String toString() {
		return "Cookie{" +
				"id=" + id +
				", cookieMessage='" + cookieMessage + '\'' +
				", luckyNumber=" + luckyNumber +
				'}';
	}

	private void numberCheck(int luckyNumber) {
		if (luckyNumber < 1) {
			throw new IllegalArgumentException("Lucky number must be POSITIVE");
		}

	}
}
