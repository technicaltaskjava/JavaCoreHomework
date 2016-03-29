package test.t03;

import t01.model.annotation.Test;
import t01.model.assertion.Assert;
import t03.model.Tuples;
import t03.model.entity.Pair;
import t03.model.entity.Triple;
import t03.model.entity.Unit;

public class TuplesTest {
	private final Unit unit = Unit.create("Name");
	private final Pair pair = Pair.create("Name", 45);
	private final Triple triple = Triple.create("Name", 45, true);

	@Test
	public void testAddTuple() {
		Tuples tuples = new Tuples();
		tuples.add(unit);
		tuples.add(pair);
		tuples.add(triple);
		Assert.assertTrue(tuples.size() == 3);
	}

	@Test
	public void testDeleteTupleSize() {
		Tuples tuples = new Tuples();
		tuples.add(unit);
		tuples.add(pair);
		tuples.add(triple);
		tuples.delete(pair);
		Assert.assertTrue(tuples.size() == 3);
	}

	@Test
	public void testDeleteTupleIndexIsNull() {
		Tuples tuples = new Tuples();
		tuples.add(unit);
		tuples.add(pair);
		tuples.add(triple);
		tuples.delete(pair);
		Assert.assertNull(tuples.getTuples()[1]);
	}
}
