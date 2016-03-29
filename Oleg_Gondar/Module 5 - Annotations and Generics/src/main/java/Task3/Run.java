package Task3;

/**
 * Created by Oleg on 27.03.2016.
 */
public class Run {

    public static void main(String[] args) {

        Unit<Integer> unit = new Unit<Integer>(1);
        System.out.println(unit);

        Pair<Integer, String> pair = new Pair<Integer, String>(1, "two");
        System.out.println(pair);

        Triplet<Integer, String, Integer> triplet = new Triplet<Integer, String, Integer>(1, "second", 3);
        System.out.println(triplet);

    }

}
