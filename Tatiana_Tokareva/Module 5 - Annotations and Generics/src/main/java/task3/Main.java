package task3;

public class Main {

    public static void main(String[] args) {
        Pair<String, Integer> pair = Pair.create("Bob", 26);
        System.out.println(pair);
        System.out.println(pair.getFirst() instanceof String);
        System.out.println(pair.getSecond() instanceof Integer);

        Triple<String, String, Integer> triple = Triple.create("Bob", "Marley", 40);
        System.out.println(triple);
        System.out.println(triple.getFirst() instanceof String);
        System.out.println(triple.getSecond() instanceof String);
        System.out.println(triple.getThird() instanceof Integer);

        Unit<Integer> unit = Unit.create(40);
        System.out.println(unit);
        System.out.println(unit.getValue() instanceof Integer);
    }
}
