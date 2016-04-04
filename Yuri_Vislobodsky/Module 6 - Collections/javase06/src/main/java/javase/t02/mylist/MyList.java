package javase.t02.mylist;

import java.util.Arrays;

/**
 * My own implementation of List interface
 * Created by Yury Vislobodsky on 02.04.2016.
 */
public class MyList<E> {
    private E[] storage;
    private int size;
    private static final int DEFAULT_CAPACITY = 16;

    public MyList() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public MyList(int capacity) {
        storage = (E[])new Object[capacity];
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    public boolean add(E element) {
        add(size, element);
        return true;
    }

    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index == -1) {
            return false;
        }
        remove(index);
        return true;
    }

    public void clear() {
        size = 0;
    }

    public E get(int index) {
        checkIndex(index);
        return storage[index];
    }

    public E set(int index, E element) {
        E previousElement = get(index);
        storage[index] = element;
        return previousElement;
    }

    public void add(int index, E element) {
        if (index != size) {
            checkIndex(index);
        }
        setSize(size + 1);
        System.arraycopy(storage, index, storage, index + 1, size - index - 1);
        set(index, element);
    }

    public E remove(int index) {
        E element = get(index);
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        setSize(size - 1);
        return element;
    }

    public int indexOf(Object o) {
        for (int index = 0; index < size; index++) {
            if (get(index).equals(o)) {
                return index;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        for (int index = size - 1; index >= 0; index--) {
            if (get(index).equals(o)) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(storage, size));
    }

    private void setSize(int newSize) {
        if (newSize > storage.length) {
            storage = Arrays.copyOf(storage, newSize * 3 / 2 + 1);
        }
        size = newSize >= 0 ? newSize : 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }
}
