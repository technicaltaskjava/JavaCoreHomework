package structures;

public class Triplet <T1,T2,T3> {
    private T1 one;
    private T2 two;
    private T3 three;

    Triplet(T1 inputOne, T2 inputTwo, T3 inputThree){
        one = inputOne;
        two = inputTwo;
        three = inputThree;
    }

    public T1 getOne(){
        return one;
    }

    public T2 getTwo(){
        return two;
    }

    public T3 getThree(){
        return three;
    }

}
