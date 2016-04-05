package com.epam.task2;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Olga Kramska on 02-Apr-16.
 */
public class CustomList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private Object[] data;
    private int size = 0;

    public CustomList() {
        this(DEFAULT_CAPACITY);
    }

    public CustomList(int initialCapacity) {
        data = new Object[initialCapacity];
    }

    public CustomList(Collection<? extends E> c) {
        data = c.toArray();
        if ((size = data.length) != 0) {
            if (data.getClass() != Object[].class) {
                data = Arrays.copyOf(data, size, Object[].class);
            }
        } else {
            this.data = new Object[0];
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object item : c) {
            if (!contains(item)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(data, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            return (T[]) Arrays.copyOf(data, size, a.getClass());//NOSONAR
        }

        System.arraycopy(data, 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    @Override
    public boolean add(E e) {
        checkCapacity(size + 1);
        data[size++] = e;
        return true;
    }

    @Override
    public void add(int index, E element) {
        addItemRangeCheck(index);

        checkCapacity(size + 1);
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
        size++;
    }

    @Override
    public E set(int index, E element) {
        rangeCheck(index);

        E oldValue = getElementData(index);
        data[index] = element;
        return oldValue;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object[] a = c.toArray();
        int numNew = a.length;
        checkCapacity(size + numNew);
        System.arraycopy(a, 0, data, size, numNew);
        size += numNew;
        return numNew != 0;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        addItemRangeCheck(index);

        Object[] a = c.toArray();
        int numNew = a.length;
        checkCapacity(size + numNew);

        int numMoved = size - index;
        if (numMoved > 0) {
            System.arraycopy(data, index, data, index + numNew, numMoved);
        }

        System.arraycopy(a, 0, data, index, numNew);
        size += numNew;
        return numNew != 0;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(data[i])) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        E value = getElementData(index);

        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(data, index + 1, data, index, numMoved);
        }

        data[--size] = null;
        return value;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return removeItems(c, false);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return removeItems(c, true);
    }

    private boolean removeItems(Collection<?> c, boolean equal) {
        boolean result = false;
        final Object[] copyData = data;
        int hit = 0;
        for (int i = 0; i < size; i++) {
            if (c.contains(data[i]) == equal) {
                copyData[hit++] = copyData[i];
            }
        }

        if (hit != size) {
            for (int i = hit; i < size; i++) {
                copyData[i] = null;
            }

            size = hit;
            result = true;
        }

        return result;
    }

    @Override
    public void clear() {
        Arrays.fill(data, null);
        size = 0;
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return getElementData(index);
    }

    @Override
    public int indexOf(Object o) {
        if (o != null) {
            for (int i = 0; i < size; i++) {
                if (o.equals(data[i])) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (data[i] == null) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o != null) {
            for (int i = size - 1; i >= 0; i--) {
                if (o.equals(data[i])) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (data[i] == null) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        subListRangeCheck(fromIndex, toIndex, size);
        E[] subList = (E[]) Arrays.copyOfRange(data, fromIndex, toIndex);//NOSONAR
        return new CustomList<>(Arrays.asList(subList));
    }

    private void rangeCheck(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException(getOutOfBoundsMsg(index));
        }
    }

    private E getElementData(int index) {
        return (E) data[index];
    }

    private String getOutOfBoundsMsg(int index) {
        return "Index: " + index + " Size: " + size;
    }

    private void checkCapacity(int capacity) {
        if (capacity > data.length) {
            int oldCapacity = data.length;
            int newCapacity = oldCapacity + oldCapacity * 2;
            data = Arrays.copyOf(data, newCapacity);
        }
    }

    private void subListRangeCheck(int fromIndex, int toIndex, int size) {
        if (fromIndex < 0) {
            throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
        }
        if (toIndex > size) {
            throw new IndexOutOfBoundsException("toIndex = " + toIndex);
        }
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
        }
    }

    private void addItemRangeCheck(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException(getOutOfBoundsMsg(index));
        }
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "CustomList{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}
