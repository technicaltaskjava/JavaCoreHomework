package model.task1.comparator;

import model.task1.entity.Aircraft;

import java.util.Comparator;

public class ComparatorByModel implements Comparator<Aircraft> {
	@Override
	public int compare(Aircraft o1, Aircraft o2) {
		if (o1 == null && o2 == null) {
			return 0;
		}
		if (o1 == null) {
			return -1;
		}
		if (o2 == null) {
			return 1;
		}
		return o1.getModel().compareTo(o2.getModel());
	}
}
