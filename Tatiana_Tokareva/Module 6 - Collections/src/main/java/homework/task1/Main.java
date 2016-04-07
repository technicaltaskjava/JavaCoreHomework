package homework.task1;

public class Main {
	private Main() {
	}

	public static void main(String[] args) {

		Gift gift = new Gift();

		gift.add(new Caramels("Roshen", 50));
		gift.add(new Caramels("Lemons", 15));
		gift.add(new Caramels("Pop", 25));


		gift.add(new Chocolate("Bounty", 35));
		gift.add(new Chocolate("Nuts", 55));
		gift.add(new Chocolate("Snikers", 30));
		gift.add(new Chocolate("AVK", 23));

		gift.add(new Wafer("KitKat", 150));
		gift.add(new Wafer("Jack", 90));

		gift.sort(new SortByWeight());
		System.out.println("Sorted by weight:\n"+gift.toString());

		gift.sort(new SortByName());
		System.out.println("Sorted by name:\n"+gift.toString());


		System.out.println("\nCount of candies in the gift="+gift.getCountAll());
		System.out.println("Weight of gift="+gift.getWeight());

		System.out.println("\nSearch:");
		System.out.println(gift.find(23));
		System.out.println(gift.find("Bounty"));
		System.out.println(gift.find(14));

	}
}
