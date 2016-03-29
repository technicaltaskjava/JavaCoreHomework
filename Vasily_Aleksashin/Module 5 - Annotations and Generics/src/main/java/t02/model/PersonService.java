package t02.model;

import t02.model.entity.Person;
import t02.model.exception.FileIOException;
import t02.model.exception.ParameterIsNullException;
import t02.util.Utility;

import java.util.Arrays;

public class PersonService {
	private Person[] persons = null;

	public Person[] getPersons(final String fileName) throws FileIOException, ParameterIsNullException {
		String[][] inputData = Utility.load(fileName);
		for (String[] strings : inputData) {
			resizePersons();
			String title = strings[0];
			int age = 0;
			try {
				age = Integer.parseInt(strings[1]);
			} catch (NumberFormatException e) {
				//set default value '0'
			}
			Person person = new Person(title, age);
			persons[persons.length - 1] = person;
		}
		return persons;
	}

	private void resizePersons() {
		if (persons == null) {
			persons = new Person[1];
		} else {
			persons = Arrays.copyOf(persons, persons.length + 1);
		}
	}
}
