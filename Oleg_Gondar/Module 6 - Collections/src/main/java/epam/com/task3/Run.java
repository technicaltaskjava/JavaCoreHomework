package epam.com.task3;

/**
 * Created by Oleg on 05.04.2016.
 */
public class Run {

    private Run() {
    }

    public static void main(String[] args) {

        NumbersHolder<Float> test = new NumbersHolder<Float>();
        test.setNumbers(198.3f);
        test.setNumbers(201f);
        test.setNumbers(250f);

        System.out.println(test.findClosest(200f));


    }

}
