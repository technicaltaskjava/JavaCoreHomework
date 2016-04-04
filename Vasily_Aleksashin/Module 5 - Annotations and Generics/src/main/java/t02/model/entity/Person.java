package t02.model.entity;

public class Person implements Comparable {
	private final String name;
	private final int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	@Override
	public int hashCode() {
		int result = name.hashCode();
		result = 31 * result + age;
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

		Person person = (Person) o;

		if (age != person.age) {
			return false;
		}
		return name.equals(person.name);

	}

	@Override
	public String toString() {
		return "Person{" +
				"name='" + name + '\'' +
				", age=" + age +
				'}';
	}

	@Override
	public int compareTo(Object o) {
		if (o == null) {
			return 1;
		}
		if (this.equals(o)) {
			return 0;
		}

		if (o instanceof Person) {
			return age - ((Person) o).getAge();
		}

		return 1;
	}
}
