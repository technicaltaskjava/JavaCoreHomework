package homework.task1;

import java.util.ArrayList;
import java.util.List;

class Checker implements Runnable {
	private List<Integer> numbers;

	private int start;
	private int end;
	private boolean flag = false;

	Checker(final int start, final int end) {
		this.start = start;
		this.end = end;
		numbers = new ArrayList<>();
	}

	Checker(final int start, final int end, final List<Integer> collectionNumbers) {
		this(start, end);
		flag = true;
		numbers = collectionNumbers;
	}

	@Override
	public void run() {
		for (int index = start; index <= end; index++) {
			if (isSimpleNumber(index)) {
				numbers.add(index);
			}
		}
	}

	List<Integer> getNumbers() {
		return numbers;
	}

	private boolean isSimpleNumber(int number) {
		int count = 0;
		for (int index = 2; index <= number; index++) {
			if (number % index == 0) {
				count++;
			}
		}
		return count == 1;
	}

	boolean isFlag() {
		return flag;
	}


}
