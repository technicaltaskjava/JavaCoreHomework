package model;

public class UserForm {
	private final String firstName;
	private final String lastName;
	private final String userName;
	private final String email;
	private final String address;
	private final String language;

	private UserForm(final Builder builder) {
		firstName = builder.firstNameBuilder;
		lastName = builder.lastNameBuilder;
		userName = builder.userNameBuilder;
		email = builder.emailBuilder;
		address = builder.addressBuilder;
		language = builder.languageBuilder;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getUserName() {
		return userName;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public String getLanguage() {
		return language;
	}

	@Override
	public int hashCode() {
		int result = firstName.hashCode();
		result = 31 * result + lastName.hashCode();
		result = 31 * result + userName.hashCode();
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
		UserForm userForm = (UserForm) o;
		return firstName.equals(userForm.firstName) && lastName.equals(userForm.lastName) && userName.equals(userForm.userName);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("UserForm{");
		builder.append("\nfirst name='").append(firstName).append("\'");
		builder.append(",\nlast name='").append(lastName).append("\'");
		builder.append(",\nuser name='").append(userName).append("\'");
		if (email != null) {
			builder.append(",\nemail='").append(email).append("\'");
		}
		if (address != null) {
			builder.append(",\naddress='").append(address).append("\'");
		}
		if (language != null) {
			builder.append(",\nlanguage='").append(language).append("\'");
		}
		builder.append("\n}");
		return builder.toString();
	}

	public static class Builder {
		private final String firstNameBuilder;
		private final String lastNameBuilder;
		private final String userNameBuilder;
		private String emailBuilder;
		private String addressBuilder;
		private String languageBuilder;

		public Builder(final String firstName, final String lastName, final String userName) {
			isNull(firstName);
			isNull(lastName);
			isNull(userName);
			firstNameBuilder = firstName;
			lastNameBuilder = lastName;
			userNameBuilder = userName;
		}

		public Builder email(final String email) {
			emailBuilder = email;
			return this;
		}

		public Builder address(final String address) {
			addressBuilder = address;
			return this;
		}

		public Builder language(final String language) {
			languageBuilder = language;
			return this;
		}

		public UserForm build() {
			return new UserForm(this);
		}

		private void isNull(final Object object) {
			if (object == null) {
				throw new IllegalArgumentException("Parameter can not be NULL");
			}
		}
	}
}