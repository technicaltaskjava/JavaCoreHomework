package homelist;

import java.util.*;

/**
 * @author Alexey Ushakov
 */

@SuppressWarnings("unused")
public class HomeList<E> implements List<E> {
    private Object[] arrayEntity;
    private int size;
    private int modificationCount;

    private class HomeIterator implements Iterator<E> {
        int current;
        int lastVisited;
        int modificationCount;

        public HomeIterator() {
            this.current = 0;
            this.lastVisited = -1;
            this.modificationCount = HomeList.this.modificationCount;
        }

        @Override
        public boolean hasNext() {
            return current < HomeList.this.size();
        }

        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            if (hasNext()) {
                this.lastVisited = current;
                current++;
                return (E) HomeList.this.arrayEntity[current];
            } else if (HomeList.this.modificationCount == modificationCount) {
                throw new ConcurrentModificationException();
            } else {
                throw new NoSuchElementException("No element on " + current + " position");
            }
        }

        @Override
        public void remove() {
            if (lastVisited < 0) {
                throw new IllegalStateException();
            } else {
                this.checkModification();

                try {
                    HomeList.this.remove(this.lastVisited);
                    this.lastVisited--;
                    this.current = this.lastVisited;
                    this.modificationCount = HomeList.this.modificationCount;
                } catch (IndexOutOfBoundsException e) {
                    throw new ConcurrentModificationException(e);
                }
            }
        }

        void checkModification() {
            if (HomeList.this.modificationCount != this.modificationCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

    private class HomeListIterator extends HomeIterator implements ListIterator<E> {
        public HomeListIterator(int position) {
            this.current = position;
            this.modificationCount = HomeList.this.modificationCount;
            this.lastVisited = -1;
        }

        public HomeListIterator() {
            this.current = 0;
            this.modificationCount = HomeList.this.modificationCount;
            this.lastVisited = -1;
        }

        @Override
        public boolean hasPrevious() {
            return current > 0;
        }

        @Override
        @SuppressWarnings("unchecked")
        public E previous() {
            if (hasPrevious()) {
                this.lastVisited = current;
                current--;
                return (E) HomeList.this.arrayEntity[current];
            } else if (HomeList.this.modificationCount == modificationCount) {
                throw new ConcurrentModificationException();
            } else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public int nextIndex() {
            if (current < HomeList.this.size) {
                return current + 1;
            } else {
                throw new IndexOutOfBoundsException();
            }
        }

        @Override
        public int previousIndex() {
            if (current > 0) {
                return current - 1;
            } else {
                throw new IndexOutOfBoundsException();
            }
        }

        @Override
        public void set(E e) {
            HomeList.this.arrayEntity[current] = e;
            modificationCount++;
            HomeList.this.modificationCount++;
        }

        @Override
        public void add(E e) {
            if (current + 1 == HomeList.this.size) {
                HomeList.this.add(current - 1, e);
            } else {
                HomeList.this.add(current + 1, e);
            }
        }
    }

    public HomeList(E[] items) {
        this.modificationCount = 0;
        this.size = items.length;
        if (items.length > 0) {
            arrayEntity = new Object[items.length];
            System.arraycopy(items, 0, arrayEntity, 0, size);
        } else {
            arrayEntity = new Object[16];
        }
    }

    public HomeList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        } else {
            this.arrayEntity = new Object[initialCapacity];
        }
        this.size = 0;
        this.modificationCount = 0;
    }

    public HomeList() {
        this.arrayEntity = new Object[16];
        this.size = 0;
        this.modificationCount = 0;
    }

    public HomeList(Collection<? extends E> c) {
        this.arrayEntity = c.toArray();
        this.size = c.size();
        this.modificationCount = 0;
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
    public Iterator<E> iterator() {
        return new HomeList<E>.HomeIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(arrayEntity, size);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] array) {
        if (array.length <= size) {
            System.arraycopy(arrayEntity, 0, array, 0, size);
            return array;
        } else {
            return (T[]) Arrays.copyOf(arrayEntity, size);//NOSONAR
        }
    }

    @SuppressWarnings("unchecked")
    private void checkPlaceForItems(int itemsCount) {
        int emptyItemsCount = arrayEntity.length - size;
        if (itemsCount < emptyItemsCount) {
            return;
        }

        int newLength = arrayEntity.length * 2;
        if (emptyItemsCount + arrayEntity.length < itemsCount) {
            newLength = arrayEntity.length + itemsCount + arrayEntity.length / 2;
        }

        Object[] newArray = new Object[newLength];
        System.arraycopy(arrayEntity, 0, newArray, 0, size);
        arrayEntity = newArray;
    }

    @Override
    public boolean add(E e) {
        checkPlaceForItems(1);
        arrayEntity[size] = e;
        size++;
        modificationCount++;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        checkPlaceForItems(collection.size());
        System.arraycopy(collection.toArray(), 0, arrayEntity, size, collection.size());
        size += collection.size();
        modificationCount++;
        return true;
    }

    @Override
    public boolean addAll(int i, Collection<? extends E> collection) {
        if (i < 0) {
            return false;
        } else {
            if (i > size - 1) {
                addAll(collection);
            } else {
                checkPlaceForItems(size - (i + collection.size()));
                System.arraycopy(collection.toArray(), 0, arrayEntity, i, collection.size());
                if (i + collection.size() > size) {
                    size = i + collection.size();
                }
            }
            modificationCount++;
            return true;
        }
    }

    private boolean isCorrectIndex(int index) {
        return (index < size) && (index >= 0);
    }

    private void removeByIndex(int index) {
        if (isCorrectIndex(index)) {
            System.arraycopy(arrayEntity, index + 1, arrayEntity, index, arrayEntity.length - index - 1);
        }
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (arrayEntity[i].equals(o)) {
                removeByIndex(i);
                return true;
            }
        }
        modificationCount++;
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        if (collection == null) {
            return false;
        } else if (collection.isEmpty()) {
            return true;
        }

        Object item;
        boolean contain = false;
        while (collection.iterator().hasNext()) {
            item = collection.iterator().next();

            for (int i = 0; i < size; i++) {
                if (arrayEntity[i].equals(item)) {
                    contain = true;
                }
            }

            if (!contain) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        if ((collection == null) || (collection.isEmpty())) {
            return false;
        }

        Object item;
        while (collection.iterator().hasNext()) {
            item = collection.iterator().next();
            for (int i = 0; i < size; i++) {
                if (arrayEntity[i].equals(item)) {
                    removeByIndex(i);
                }
            }
        }
        modificationCount++;
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        if ((collection == null) || (collection.isEmpty())) {
            return false;
        }

        Object item;
        while (collection.iterator().hasNext()) {
            item = collection.iterator().next();
            for (int i = 0; i < size; i++) {
                if (!arrayEntity[i].equals(item)) {
                    removeByIndex(i);
                }
            }
        }
        modificationCount++;
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        arrayEntity = new Object[16];
        size = 0;
        modificationCount++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int i) {
        if (isCorrectIndex(i)) {
            return (E) arrayEntity[i];
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E set(int i, E e) {
        if (isCorrectIndex(i)) {
            arrayEntity[i] = e;
            modificationCount++;
            if (i != 0) {
                return (E) arrayEntity[i - 1];
            }
        }
        return (E) arrayEntity[0];
    }

    @Override
    public void add(int i, E e) {
        if (isCorrectIndex(i)) {
            checkPlaceForItems(1);
            System.arraycopy(arrayEntity, i - 1, arrayEntity, i, arrayEntity.length - i);
            arrayEntity[i] = e;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public E remove(int i) {
        if (isCorrectIndex(i)) {
            removeByIndex(i);
            if (i != 0) {
                return (E) arrayEntity[i - 1];
            }
        }
        return (E) arrayEntity[0];
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (arrayEntity[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (arrayEntity[i].equals(o)) {
                index = i;
            }
        }
        return index;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new HomeList<E>.HomeListIterator();
    }

    @Override
    public ListIterator<E> listIterator(int i) {
        return new HomeList<E>.HomeListIterator(i);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<E> subList(int i, int i1) {
        if (isCorrectIndex(i) && isCorrectIndex(i1) && (i < i1)) {
            return new HomeList(Arrays.copyOfRange(arrayEntity, i, i1));
        }
        return new HomeList<>(0);
    }

}