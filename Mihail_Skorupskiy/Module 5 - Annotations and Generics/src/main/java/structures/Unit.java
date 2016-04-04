package structures;

public class Unit <T1> {
    private T1 one;

    Unit(T1 input){
        one = input;
    }

    public T1 getOne(){
        return one;
    }
}
