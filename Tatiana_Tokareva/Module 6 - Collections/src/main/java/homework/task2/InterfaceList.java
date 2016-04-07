package homework.task2;


import java.util.*;

public class InterfaceList<E> implements List {
    private static final int MAX_SIZE = 10;
    private Object[] array;
    private int size = 0;


    public InterfaceList() {
        array = new Object[MAX_SIZE];
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public boolean isEmpty() {
         return size==0;

    }


    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size(); i++) {
            if (get(i).equals(o))
                return true;
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {

        return new Object[0];

    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    @Override
    public boolean add(Object o) {
        checkCapacity(size + 1);
        array[size++] = o;
        return true;
    }

    @Override
    public boolean remove(Object o) {

        for (int i = 0; i < size; i++) {
            if (o.equals(array[i])) {
                array[i] = null;
                return true;
            }
        }
        return false;

    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {

        return false;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public Object get(int index) {

        return null;
    }

    @Override
    public Object set(int index, Object element) {
        return array[index] = element;
    }

    @Override
    public void add(int index, Object element) {

        set(index, element);

    }

    @Override
    public Object remove(int index) {
        return array[index] = null;
    }

    @Override
    public int indexOf(Object o) {

        for (int index = 0; index < size; index++) {
            if (o.equals(array[index]))
                return index;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int index = size - 1; index >= 0; index--) {
            if (o.equals(array[index]))
                return index;
        }
        return -1;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public String toString() {
        return
                "array=" + Arrays.toString(array);
    }

    private void checkCapacity(int capacity) {
        if (capacity > array.length) {
            int oldCapacity = array.length;
            int newCapacity = oldCapacity + oldCapacity * 2;
            array = Arrays.copyOf(array, newCapacity);
        }
    }
}
