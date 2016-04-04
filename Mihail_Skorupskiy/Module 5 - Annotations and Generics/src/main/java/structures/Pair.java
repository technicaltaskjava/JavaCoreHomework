package structures;

public class Pair <T1,T2> {
    private T1 one;
    private T2 two;

    Pair(T1 inputOne, T2 inputTwo){
        one = inputOne;
        two = inputTwo;
    }

    public T1 getOne(){
        return one;
    }

    public T2 getTwo(){
        return two;
    }
}
