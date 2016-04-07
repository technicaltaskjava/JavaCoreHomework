package homework.task1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Gift {
	private List<Candies> sweets = new ArrayList<>();


	public void add(Candies candies) {
		sweets.add(candies);
	}

	public void sort(Comparator<Candies> comparator) {
		Collections.sort(sweets, comparator);
	}

	public int getCountAll() {
		return sweets.size();
	}



	public int getWeight() {
		int count = 0;
		for (Candies candies : sweets) {
			count += candies.getWeight();
		}
		return count;
	}



	public Candies find(String name) {
		for (Candies candies : sweets) {
			if (candies.getName().equals(name)) {
				return candies;
			}
		}
		return null;
	}

	public Candies find(int weight) {
		for (Candies candies : sweets) {
			if (candies.getWeight() == weight) {
				return candies;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Gift{" +
				"gift=" + sweets +
				'}';
	}
}
