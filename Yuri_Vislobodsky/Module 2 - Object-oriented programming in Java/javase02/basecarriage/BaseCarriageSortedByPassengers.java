package javase02.basecarriage;

import java.util.Comparator;

/**
 * Class for sorting by passengers for comparator 
 * @author YURY.VISLOBODSKY
 * 
 */

public class BaseCarriageSortedByPassengers implements Comparator<BaseCarriage> {
	public int compare(BaseCarriage carriage1, BaseCarriage carriage2) {
		int elem1 = carriage1.getPassengersNumber();
		int elem2 = carriage2.getPassengersNumber();
		if (elem1 < elem2) {
			return -1;
		} else if (elem1 > elem2) {
			return 1;
		} else {
			return 0;
		}
	}
}