package homework.builder;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OrderTest {
	private Order order;

	@Before
	public void setUp() {
		order = new Order.Builder()
				.coffeeBuild(2)
				.hamburgerBuild(1)
				.nuggetsBuild(2)
				.crispyBuild(3)
				.build();
	}

	@Test
	public void testToString() {
		assertEquals("Order{coffee='2', nuggets='2', hamburger='1', crispy='3'}", order.toString());
	}
}