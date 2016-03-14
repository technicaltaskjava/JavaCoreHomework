package javase02.basecarriage;

import java.util.Comparator;

/**
 * Class for sorting by baggage weight for comparator 
 * @author YURY.VISLOBODSKY
 * 
 */

public class BaseCarriageSortedByWeight implements Comparator<BaseCarriage> {
	public int compare(BaseCarriage carriage1, BaseCarriage carriage2) {
		int elem1 = carriage1.getBaggageWeightInKg();
		int elem2 = carriage2.getBaggageWeightInKg();
		if (elem1 < elem2) {
			return -1;
		} else if (elem1 > elem2) {
			return 1;
		} else {
			return 0;
		}
	}
}