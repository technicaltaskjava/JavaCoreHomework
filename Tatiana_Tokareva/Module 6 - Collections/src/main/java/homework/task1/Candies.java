package homework.task1;

public abstract class Candies {

    protected String name;
	protected int weight;

    public String getName() {
        return name;
    }

	public int getWeight() {
        return weight;
    }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Candies candies = (Candies) o;

		if (weight != candies.weight) return false;
		return name != null ? name.equals(candies.name) : candies.name == null;

	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + weight;
		return result;
	}

	@Override
	public String toString() {
		return "Candies{" +
				"name='" + name + '\'' +
				", weight=" + weight +
				'}';
	}
}

