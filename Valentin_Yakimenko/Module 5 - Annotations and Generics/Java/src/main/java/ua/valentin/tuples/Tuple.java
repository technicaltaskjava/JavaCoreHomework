package ua.valentin.tuples;

public class Tuple {
    static class Tuple2<Type1, Type2> {
        public Type1 type1;
        public Type2 type2;
    }

    static class Tuple3<Type1, Type2, Type3> extends Tuple2<Type1, Type2> {
        public Type3 type3;
    }
    static Tuple2<Integer, String> split(String input) {
        String[] splited = input.split(",");
        Tuple2<Integer, String> output = new Tuple2<Integer, String>();
        output.type1 = Integer.valueOf(splited[0]);
        output.type2 = splited[1];

        return output;
    }

    public static void main(String...args) {
        final Tuple2<Integer, String> splited = split("10,number");
        System.out.println(String.format("This is %s %d", splited.type2, splited.type1));

        final Tuple3<String, Integer, Boolean> tuple3 = new Tuple3<String, Integer, Boolean>();
        tuple3.type1 = "string";
        tuple3.type2 = 10;
        tuple3.type3 = true;
        System.out.println(String.format("type1: %s, type2: %d, type3: %b", tuple3.type1, tuple3.type2, tuple3.type3));

    }
 }
