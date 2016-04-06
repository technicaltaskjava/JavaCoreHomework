package ThirdTask;


public class ThirdTask {
    public static void main(String[] args) {
        Unit <String> unit = new Unit<String>("Unit<T>");
        Pair <String, Integer> pair = new Pair<String, Integer>("Pair<T, T2>", 23442);
        Triplet<String, Integer, String> triplet = new Triplet<String, Integer, String>("Triplet<T, T2, T3>", 10091, "@hashCode");


        System.out.println(unit.toString());
        System.out.println(pair.toString());
        System.out.println(triplet.toString());

    }
}
