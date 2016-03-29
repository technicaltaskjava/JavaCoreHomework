package t03.model;

import t02.model.exception.ParameterIsNullException;
import t02.util.Utility;

import java.util.Arrays;

public class Tuples {
	private Tuple[] tuples = null;

	public void add(final Tuple tuple) {
		try {
			Utility.validate(tuple);
			int index = findTupleIndex(tuple);
			if (index < 0) {
				resizeTuples();
				tuples[tuples.length - 1] = tuple;
			}
		} catch (ParameterIsNullException e) {
			//do nothing
		}
	}

	public void delete(final Tuple tuple) {
		try {
			Utility.validate(tuple);
			int index = findTupleIndex(tuple);
			if (index >= 0) {
				tuples[index] = null;
			}
		} catch (ParameterIsNullException e) {
			//do nothing
		}
	}

	public Tuple[] getTuples() {
		return tuples != null ? Arrays.copyOf(tuples, tuples.length) : tuples;
	}

	public int size() {
		return tuples != null ? tuples.length : -1;
	}

	private int findTupleIndex(final Tuple tuple) {
		if (tuples == null) {
			return -1;
		}
		for (int index = 0; index < tuples.length; index++) {
			if (tuple.equals(tuples[index])) {
				return index;
			}
		}
		return -1;
	}

	private void resizeTuples() {
		if (tuples == null) {
			tuples = new Tuple[1];
		} else {
			tuples = Arrays.copyOf(tuples, tuples.length + 1);
		}
	}
}
