package test.t02.model.entity;

import t01.model.annotation.Test;
import t01.model.assertion.Assert;
import t02.model.entity.Person;

public class PersonTest {

	@Test
	public void testGetAge() {
		int age = 25;
		Person person = new Person("Bob", age);
		Assert.assertTrue(person.getAge() == age);
	}

	@Test
	public void testCompareToWithNull() {
		Person person = new Person("Bob", 20);
		Assert.assertTrue(person.compareTo(null) == 1);
	}

	@Test
	public void testCompareToWithDiffPerson() {
		Person person = new Person("Bob", 20);
		Assert.assertTrue(person.compareTo(new Person("Bob", 20)) == 0);
	}

	@Test
	public void testCompareToWithObject() {
		Person person = new Person("Bob", 20);
		Assert.assertTrue(person.compareTo(new Object()) == 1);
	}

	@Test
	public void testCompareToWithDiffPersonYounger() {
		Person person = new Person("Bob", 20);
		Assert.assertTrue(person.compareTo(new Person("Bob", 15)) > 0);
	}

	@Test
	public void testCompareToWithDiffPersonOlder() {
		Person person = new Person("Bob", 20);
		Assert.assertTrue(person.compareTo(new Person("Bob", 25)) < 0);
	}
}
