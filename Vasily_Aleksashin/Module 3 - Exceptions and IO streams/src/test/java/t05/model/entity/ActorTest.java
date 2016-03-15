package t05.model.entity;

import org.junit.Before;
import org.junit.Test;
import t01.exception.ModelException;

import static org.junit.Assert.*;

public class ActorTest {
	private static final String NAME = "Jon Smit";
	private Actor actor;

	@Before
	public void setUp() throws ModelException {
		actor = new Actor(NAME);
	}

	@Test(expected = ModelException.class)
	public void testActorWithNull() throws ModelException {
		new Actor(null);
	}

	@Test
	public void testGetName() {
		assertEquals(NAME, actor.getName());
	}

	@Test
	public void testEqualsReflexive() {
		assertTrue(actor.equals(actor));
	}

	@Test
	public void testEqualsName() throws ModelException {
		assertTrue(actor.equals(new Actor(NAME)));
	}

	@Test
	public void testEqualsNull() throws ModelException {
		assertFalse(actor.equals(null));
	}

	@Test
	public void testEqualsObject() throws ModelException {
		assertFalse(actor.equals(new Object()));
	}

	@Test
	public void testEqualsDiffName() throws ModelException {
		assertFalse(actor.equals(new Actor("Bob")));
	}

	@Test
	public void testHashCode() {
		assertTrue(actor.hashCode() == NAME.hashCode());
	}

	@Test
	public void testToString() {
		assertEquals("Actor{name='Jon Smit'}", actor.toString());
	}
}