package epam.com.task2;

import java.util.*;

/**
 * Created by Oleg on 05.04.2016.
 */
public class MyArrayListImpl<T> implements List<T>, java.io.Serializable {

    private static final int DEFAULT_SIZE = 10;

    private transient Object[] elements;
    private int size;
    private int index = 0;

    public MyArrayListImpl() {
        this(DEFAULT_SIZE);
    }

    public MyArrayListImpl(int size) {
        this.elements = new Object[size];
        this.size = size;
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
        for (Object t :
                elements) {
            if (t.equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator<>();
    }

    @Override
    public Object[] toArray() {
        return elements;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return (T1[]) elements;
    }

    @Override
    public boolean add(T t) {

        if (index == elements.length) {
            Object[] temp = new Object[elements.length + 1];
            System.arraycopy(elements, 0, temp, 0, elements.length);
            elements = temp;
        }
        elements[index] = t;
        index++;
        size = elements.length;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++)
            if (o.equals(elements[i]) && (size - i - 1 > 0)) {

                System.arraycopy(elements, i + 1, elements, i, size - i - 1);
                size = elements.length;
                return true;

            }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Iterator<?> e = c.iterator();
        while (e.hasNext()) {
            if (!contains(e.next())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {

        Object[] a = c.toArray();
        int newSize = a.length;
        ensureCapacity(size + newSize);
        System.arraycopy(a, 0, elements, size, newSize);
        size += newSize;
        return newSize != 0;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Object[] a = c.toArray();
        int newSize = a.length;
        ensureCapacity(size + newSize);
        if (size - index - 1 > 0) {
            System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        }
        System.arraycopy(a, 0, elements, index, newSize);
        size += newSize;
        return newSize != 0;
    }

    @Override
    public boolean removeAll(Collection<?> c) {

        int deleted = 0;
        Iterator<?> e = c.iterator();
        while (e.hasNext()) {
            if (contains(e.next())) {
                remove(e.next());
                size--;
                deleted++;
            }
        }
        return deleted != 0;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int deleted = 0;
        Iterator<?> e = c.iterator();
        while (e.hasNext()) {
            if (!contains(e.next())) {
                remove(e.next());
                size--;
                deleted++;
            }
        }
        return deleted == 0;
    }

    @Override
    public void clear() {

        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;

    }

    @Override
    public T get(int index) {
        if (index < size) {
            return (T) elements[index];
        }
        return null;
    }

    @Override
    public T set(int index, T element) {
        if (index < size) {
            elements[index] = element;
            return (T) elements[index];
        }
        return null;
    }

    @Override
    public void add(int index, T element) {

        ensureCapacity(size + 1);
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;

    }

    @Override
    public T remove(int index) {
        T ret = (T) elements[index];
        if (size - index - 1 > 0) {
            System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        }
        return ret;
    }

    @Override
    public int indexOf(Object o) {

        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        int lastIndexOf = 0;
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                lastIndexOf = i;
            }
        }
        return lastIndexOf;
    }

    @Override
    public MyListIterator<T> listIterator() {
        return new MyListIterator<>(0);
    }

    @Override
    public MyListIterator<T> listIterator(int index) {
        return new MyListIterator<>(index);

    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        List<T> list = new ArrayList<>();
        if (fromIndex < toIndex && toIndex < size) {
            for (int i = fromIndex; i < toIndex; i++) {
                list.add((T) elements[i]);
            }
            return list;
        }
        return new ArrayList<>();
    }

    public void ensureCapacity(int minCapacity) {

        int oldCapacity = elements.length;
        if (minCapacity > oldCapacity) {

            int newCapacity = (oldCapacity * 3) / 2 + 1;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }


            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    private class MyListIterator<T> implements java.util.ListIterator<T> {

        int iteration = index;

        public MyListIterator(int iteration) {
            this.iteration = iteration;
        }

        @Override
        public boolean hasNext() {
            if (iteration < size && elements[iteration + 1] != null) {
                return true;
            }
            return false;
        }

        @Override
        public T next() {
            if (iteration + 1 < size) {
                iteration++;
                return (T) elements[iteration - 1];
            } else if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return null;
        }

        @Override
        public boolean hasPrevious() {

            if (iteration - 1 > 0) {
                return true;
            }
            return false;
        }

        @Override
        public T previous() {
            if (iteration - 1 > 0) {
                iteration--;
                return (T) elements[iteration];
            }
            return null;
        }

        @Override
        public int nextIndex() {
            return iteration + 1;
        }

        @Override
        public int previousIndex() {
            return iteration - 1;
        }

        @Override
        public void remove() {
            if (size - iteration - 1 > 0) {
                System.arraycopy(elements, iteration + 1, elements, iteration, size - iteration - 1);
            }
        }

        @Override
        public void set(T t) {
            elements[iteration] = t;
        }

        @Override
        public void add(T t) {
            add(t);
        }
    }

    private class MyIterator<T> implements Iterator<T> {
        int iter = 0;

        @Override
        public boolean hasNext() {
            if (size > iter + 1) {
                return elements[iter + 1] != null;
            }
            return false;
        }

        @Override
        public T next() {

            if (hasNext()) {
                iter++;
                return (T) elements[iter - 1];
            } else if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return null;
        }
    }

}
