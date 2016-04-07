package model.task1.comparator;

import exception.ParameterValidateException;
import model.task1.entity.Airliner;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ComparatorByModelTest {
	private static final String BOEING = "Boeing";
	private static final String AN = "AN";
	private final ComparatorByModel comparator = new ComparatorByModel();

	@Test
	public void testCompareTwoNull() {
		assertTrue(comparator.compare(null, null) == 0);
	}

	@Test
	public void testCompareFirstNull() throws ParameterValidateException {
		final Airliner airliner = new Airliner(BOEING);
		assertTrue(comparator.compare(null, airliner) < 0);
	}

	@Test
	public void testCompareSecondNull() throws ParameterValidateException {
		final Airliner airliner = new Airliner(BOEING);
		assertTrue(comparator.compare(airliner, null) > 0);
	}

	@Test
	public void testCompareSamePlan() throws ParameterValidateException {
		final Airliner airliner = new Airliner(BOEING);
		assertTrue(comparator.compare(airliner, airliner) == 0);
	}

	@Test
	public void testCompareFirstIsMore() throws ParameterValidateException {
		final Airliner airlinerFirst = new Airliner(BOEING);
		final Airliner airlinerSecond = new Airliner(AN);
		assertTrue(comparator.compare(airlinerFirst, airlinerSecond) > 0);
	}

	@Test
	public void testCompareFirstIsLess() throws ParameterValidateException {
		final Airliner airlinerFirst = new Airliner(AN);
		final Airliner airlinerSecond = new Airliner(BOEING);
		assertTrue(comparator.compare(airlinerFirst, airlinerSecond) < 0);
	}
}