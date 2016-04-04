package structures;

public class Tuples {

    public static <I1> Unit create(I1 inputOne){
        return new Unit(inputOne);
    }

    public static <I1, I2> Pair create(I1 inputOne, I2 inputTwo){
        return new Pair(inputOne, inputTwo);
    }

    public static <I1, I2, I3> Triplet create(I1 inputOne, I2 inputTwo, I3 inputThree){
        return new Triplet(inputOne, inputTwo, inputThree);
    }
}
