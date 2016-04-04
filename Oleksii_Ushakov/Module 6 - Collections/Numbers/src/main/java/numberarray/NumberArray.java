package numberarray;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Alexey Ushakov
 */

@SuppressWarnings("unused")
public class NumberArray<E extends Number> {
    private List<E> arrayNumber;

    public NumberArray() {
        this.arrayNumber = new LinkedList<>();
    }

    public NumberArray(E[] array) {
        this.arrayNumber = new LinkedList<>();
        Collections.addAll(this.arrayNumber, array);
    }

    public int size() {
        return arrayNumber.size();
    }

    public void add(E entity) {
        arrayNumber.add(entity);
    }

    public void remove(E entity) {
        arrayNumber.remove(entity);
    }

    public boolean isEmpty() {
        return arrayNumber.isEmpty();
    }

    private double getDifferenceModule(E first, E second) {
        return Math.abs(first.doubleValue() - second.doubleValue());
    }

    public E find(E entity) {
        if (!arrayNumber.isEmpty()) {
            int returnIndex = 0;
            int currentIndex = 0;
            Iterator<E> iterator = arrayNumber.iterator();

            E checkEntity = iterator.next();
            double differenceModule = getDifferenceModule(entity, checkEntity);


            while (iterator.hasNext()) {
                checkEntity = iterator.next();
                currentIndex++;

                if (differenceModule > getDifferenceModule(entity, checkEntity)) {
                    differenceModule = getDifferenceModule(entity, checkEntity);
                    returnIndex = currentIndex;
                }
            }

            return arrayNumber.get(returnIndex);
        } else {
            throw new NullPointerException();
        }
    }
}