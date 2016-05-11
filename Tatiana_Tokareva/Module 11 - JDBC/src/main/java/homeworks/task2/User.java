package homeworks.task2;

public class User {

	private int id;
	private String lastName;
	private String firtsName;
	private String email;
	private String password;


	public User(final int id, final String lastName, final String firtsName, final String email, final String password) {
		this.id = id;
		this.lastName = lastName;
		this.firtsName = firtsName;
		this.email = email;
		this.password = password;
	}

	public User(final String lastName, final String firtsName, final String email, final String password) {
		this.lastName = lastName;
		this.firtsName = firtsName;
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirtsName() {
		return firtsName;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public void setFirtsName(final String firtsName) {
		this.firtsName = firtsName;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	@Override
	public String toString() {

		String result = this.getClass().getSimpleName();
		result += " id=" + getId()+
				", lastName='" + lastName + '\'' +
				", firtsName='" + firtsName + '\'' +
				", password='" + password + "\n";
		return result;
	}
}
