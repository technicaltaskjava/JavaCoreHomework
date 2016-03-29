package t02.model;

import t02.model.exception.ParameterIsNullException;
import t02.util.Utility;

import java.util.Arrays;
import java.util.Comparator;

public class CompareUtils {
	private CompareUtils() {
	}

	public static <T extends Comparable> T min(final T[] array) throws ParameterIsNullException {
		T[] temp = preparedArray(array);
		return temp[0];
	}

	public static <T> T min(final T[] array, final Comparator<T> comparator) throws ParameterIsNullException {
		T[] temp = preparedArray(array, comparator);
		return comparator.compare(temp[0], temp[temp.length - 1]) < 0 ? temp[0] : temp[temp.length - 1];
	}

	public static <T extends Comparable> T max(final T[] array) throws ParameterIsNullException {
		T[] temp = preparedArray(array);
		return temp[temp.length - 1];
	}

	public static <T> T max(final T[] array, final Comparator<T> comparator) throws ParameterIsNullException {
		T[] temp = preparedArray(array, comparator);
		return comparator.compare(temp[0], temp[temp.length - 1]) > 0 ? temp[0] : temp[temp.length - 1];
	}

	public static <T extends Comparable> T median(final T[] array) throws ParameterIsNullException {
		T[] temp = preparedArray(array);
		return temp[temp.length / 2];
	}

	public static <T> T median(final T[] array, final Comparator<T> comparator) throws ParameterIsNullException {
		T[] temp = preparedArray(array, comparator);
		return temp[temp.length / 2];
	}

	private static <T extends Comparable> T[] preparedArray(T[] array) throws ParameterIsNullException {
		validate(array);
		T[] temp = Arrays.copyOf(array, array.length);
		Arrays.sort(temp);
		return temp;
	}

	private static <T> T[] preparedArray(T[] array, Comparator<T> comparator) throws ParameterIsNullException {
		validate(array);
		Utility.validate(comparator);
		T[] temp = Arrays.copyOf(array, array.length);
		Arrays.sort(temp, comparator);
		return temp;
	}

	private static <T extends Comparable> void validate(final T[] array) throws ParameterIsNullException {
		Utility.validate(array);
		for (T element : array) {
			Utility.validate(element);
		}
	}

	private static <T> void validate(final T[] array) throws ParameterIsNullException {
		Utility.validate(array);
		for (T element : array) {
			Utility.validate(element);
		}
	}
}
