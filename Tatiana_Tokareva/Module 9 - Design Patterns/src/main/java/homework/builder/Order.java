package homework.builder;

public class Order {
	private final int coffee;
	private final int nuggets;
	private final int hamburger;
	private final int crispy;

	private Order(final Builder builder) {
		coffee = builder.coffeeBuilder;
		nuggets = builder.nuggetsBuilder;
		hamburger = builder.hamburgerBuilder;
		crispy = builder.crispyBuilder;
	}

	@Override
	public String toString() {
		return "Order{" +
				"coffee='" + coffee + '\'' +
				", nuggets='" + nuggets + '\'' +
				", hamburger='" + hamburger + '\'' +
				", crispy='" + crispy + '\'' +
				'}';
	}

	public static class Builder {
		private int coffeeBuilder;
		private int nuggetsBuilder;
		private int hamburgerBuilder;
		private int crispyBuilder;


		public Builder coffeeBuild(int coffee) {
			this.coffeeBuilder = coffee;
			return this;
		}

		public Builder nuggetsBuild(int nuggets) {
			this.nuggetsBuilder = nuggets;
			return this;
		}

		public Builder hamburgerBuild(int hamburger) {
			this.hamburgerBuilder = hamburger;
			return this;
		}

		public Builder crispyBuild(int crispy) {
			this.crispyBuilder = crispy;
			return this;
		}

		public Order build() {
			return new Order(this);
		}
	}
}
