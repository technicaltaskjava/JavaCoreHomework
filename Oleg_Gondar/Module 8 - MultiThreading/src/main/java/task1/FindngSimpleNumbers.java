package task1;

import java.util.Set;
import java.util.TreeSet;


/**
 * Created by Oleg on 17.04.2016.
 */
public class FindngSimpleNumbers implements Runnable {

    private int startNumber;
    private int endNumber;
    private Set<Integer> simpleNumbers;

    public FindngSimpleNumbers(int startNumber, int endNumber, Set<Integer> simpleNumbers) {
        this.startNumber = startNumber;
        this.endNumber = endNumber;
        this.simpleNumbers = simpleNumbers;
    }

    public void findSimpleNumbers() {

        for (int i = startNumber; i <= endNumber; i++) {
            boolean isSimple = true;
            int iterator = 2;
            while (isSimple && iterator < i ) {
                if (i % iterator == 0) {
                    isSimple = false;
                }
                iterator++;
            }
            if (isSimple) {
                synchronized (simpleNumbers) {
                    simpleNumbers.add(i);
                }
            }
        }
    }

    public void run() {
        findSimpleNumbers();
    }
}
