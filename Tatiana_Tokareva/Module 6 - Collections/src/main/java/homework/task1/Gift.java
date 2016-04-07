package homework.task1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Gift {
	private List<Candies> gift = new ArrayList<>();

	public void add(Candies candies) {
		gift.add(candies);
	}

	public void sort(Comparator<Candies> comparator) {
		Collections.sort(gift, comparator);
	}

	public int getCountAll() {
		return gift.size();
	}



	public int getWeight() {
		int count = 0;
		for (Candies candies : gift) {
			count += candies.getWeight();
		}
		return count;
	}



	public Candies find(String name) {
		for (Candies candies : gift) {
			if (candies.getName().equals(name)) {
				return candies;
			}
		}
		return null;
	}

	public Candies find(int weight) {
		for (Candies candies : gift) {
			if (candies.getWeight() == weight) {
				return candies;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Gift{" +
				"gift=" + gift +
				'}';
	}
}
