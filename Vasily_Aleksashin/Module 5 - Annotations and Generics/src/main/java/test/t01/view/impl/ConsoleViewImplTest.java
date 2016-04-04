package test.t01.view.impl;

import t01.model.annotation.Test;
import t01.view.View;
import t01.view.impl.ConsoleViewImpl;

import static t01.model.assertion.Assert.assertFalse;

public class ConsoleViewImplTest {
	private final View view = new ConsoleViewImpl();

	@Test
	public void testCloseFalse() {
		assertFalse(view.close());
	}
}