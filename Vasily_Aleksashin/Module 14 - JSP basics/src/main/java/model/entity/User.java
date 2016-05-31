package model.entity;

import utility.Validator;

public class User implements Identified {
	private static final int MIN_PASSWORD_LENGTH = 6;
	private static final int MIN_AGE = 1;
	private static final int MAX_AGE = 99;

	private Integer id;
	private String userName;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private int age;

	@Override
	public Integer getId() {
		return id;
	}

	protected void setId(final Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		Validator.isNull(userName, "User name");
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		Validator.isNull(email, "Email");
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		Validator.isNull(password, "Password");
		passwordCheck(password);
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		Validator.isNull(firstName, "First name");
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		Validator.isNull(firstName, "Last name");
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(final int age) {
		ageCheck(age);
		this.age = age;
	}

	@Override
	public int hashCode() {
		int result = userName.hashCode();
		result = 31 * result + email.hashCode();
		return result;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		User user = (User) o;
		return userName.equals(user.userName) && email.equals(user.email);
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", userName='" + userName + '\'' +
				", email='" + email + '\'' +
				", pass='" + password + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", age=" + age +
				'}';
	}

	private void passwordCheck(final String password) {
		if (password.length() < MIN_PASSWORD_LENGTH) {
			throw new IllegalArgumentException(String.format("Password length must be more then %s characters", MIN_PASSWORD_LENGTH));
		}
	}

	private void ageCheck(final int age) {
		if (age < MIN_AGE || age > MAX_AGE) {
			throw new IllegalArgumentException(String.format("Age must be between %s to %s", MIN_AGE, MAX_AGE));
		}
	}
}
