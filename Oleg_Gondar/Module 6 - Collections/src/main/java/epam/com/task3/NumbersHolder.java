package epam.com.task3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by O.Gondar on 05.04.2016.
 */
public class NumbersHolder<T extends Number & Comparable> {

    private List<T> numbers;

    public NumbersHolder() {
        this.numbers = new ArrayList<T>();
    }

    public T getNumbers(int index) {

        return numbers.get(index);

    }

    public void setNumbers(T number) {
        numbers.add(number);
    }

    public int deleteNumber(MyNumber number) {

        int count = 0;
        for (T m :
                numbers) {
            if (m.equals(number)) {
                numbers.remove(m);
                count++;
            }
        }

        return count;
    }

    public T findClosest(T number) {

        Double delta = Math.abs(number.doubleValue() - numbers.get(0).doubleValue());
        Double temp;
        T closest = numbers.get(0);

        for (T m :
                numbers) {
            temp = Math.abs(number.doubleValue() - m.doubleValue());
            if (temp < delta) {
                delta = temp;
                closest = m;
            }
        }
        return closest;
    }

}
