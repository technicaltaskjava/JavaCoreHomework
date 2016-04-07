package model.task1.comparator;

import exception.ParameterValidateException;
import model.task1.entity.Airliner;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ComparatorByFlyingRangeTest {
	private static final String MODEL = "Boeing";
	private static final int RANGE = 1000;
	private final ComparatorByFlyingRange comparator = new ComparatorByFlyingRange();

	@Test
	public void testCompareTwoNull() {
		assertTrue(comparator.compare(null, null) == 0);
	}

	@Test
	public void testCompareFirstNull() throws ParameterValidateException {
		final Airliner airliner = new Airliner(MODEL);
		assertTrue(comparator.compare(null, airliner) < 0);
	}

	@Test
	public void testCompareSecondNull() throws ParameterValidateException {
		final Airliner airliner = new Airliner(MODEL);
		airliner.setFlyingRange(RANGE);
		assertTrue(comparator.compare(airliner, null) > 0);
	}

	@Test
	public void testCompareSamePlan() throws ParameterValidateException {
		final Airliner airliner = new Airliner(MODEL);
		airliner.setFlyingRange(RANGE);
		assertTrue(comparator.compare(airliner, airliner) == 0);
	}

	@Test
	public void testCompareFirstIsMore() throws ParameterValidateException {
		final Airliner airlinerFirst = new Airliner(MODEL);
		airlinerFirst.setFlyingRange(RANGE);
		final Airliner airlinerSecond = new Airliner(MODEL);
		assertTrue(comparator.compare(airlinerFirst, airlinerSecond) > 0);
	}

	@Test
	public void testCompareFirstIsLess() throws ParameterValidateException {
		final Airliner airlinerFirst = new Airliner(MODEL);
		final Airliner airlinerSecond = new Airliner(MODEL);
		airlinerSecond.setFlyingRange(RANGE);
		assertTrue(comparator.compare(airlinerFirst, airlinerSecond) < 0);
	}
}