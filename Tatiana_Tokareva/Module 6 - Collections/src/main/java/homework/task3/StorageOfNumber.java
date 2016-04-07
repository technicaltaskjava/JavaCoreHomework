package homework.task3;

import java.util.*;

public class StorageOfNumber {
    private List<Double> data = new ArrayList<>();

    public void add(Double number) {
        data.add(number);
    }

    public boolean delete(Double number) {
        return data.remove(number);
    }

    public Double find(Double number) {
        Iterator iterator = data.iterator();
        Double min = (Double) iterator.next();

        while (iterator.hasNext()) {
            Double elem = (Double) iterator.next();
            if (Math.abs(elem - number) < Math.abs(min - number)) {
                min = elem;
            }
        }
        return min;
    }

    public String getData() {
        return data.toString();
    }

}
