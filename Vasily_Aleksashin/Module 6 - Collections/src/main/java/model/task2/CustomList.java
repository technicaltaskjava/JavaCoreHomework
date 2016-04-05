package model.task2;

import java.util.*;

public class CustomList<T> implements List<T> {
	private static final int DEFAULT_CAPACITY = 10;
	private static final String INDEX_IS_OUT_OF_RANGE = "Index is out of range";

	private Object[] elements;
	private int countElement = 0;

	public CustomList(final int initCapacity) {
		if (initCapacity > 0) {
			elements = new Object[initCapacity];
		} else if (initCapacity == 0) {
			elements = new Object[]{};
		} else {
			throw new IllegalArgumentException("Initial capacity incorrect");
		}
	}

	public CustomList() {
		this(DEFAULT_CAPACITY);
	}

	@Override
	public int size() {
		return countElement;
	}

	@Override
	public boolean isEmpty() {
		return countElement == 0;
	}

	@Override
	public boolean contains(Object o) {
		return indexOf(o) >= 0;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iter();
	}

	@Override
	public Object[] toArray() {
		return Arrays.copyOf(elements, countElement);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a) {
		if (a.length < countElement) {
			return (T[]) Arrays.copyOf(elements, countElement, a.getClass()); // NOSONAR
		}
		System.arraycopy(elements, 0, a, 0, countElement);
		if (a.length > countElement) {
			a[countElement] = null;
		}
		return a;
	}

	@Override
	public boolean add(T t) {
		resizeCapacity(1);
		elements[countElement++] = t;
		return true;
	}
	
	@Override
	public boolean remove(Object o) {
		if (o == null) {
			for (int index = 0; index < countElement; index++)
				if (elements[index] == null) {
					fastRemove(index);
					return true;
				}
		} else {
			for (int index = 0; index < countElement; index++)
				if (o.equals(elements[index])) {
					fastRemove(index);
					return true;
				}
		}
		return false;
	}
	
	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object element : c) {
			if (!contains(element)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		Object[] objects = c.toArray();
		int length = objects.length;
		resizeCapacity(length);
		System.arraycopy(objects, 0, elements, countElement, length);
		countElement += length;
		return length != 0;
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		checkIndex(index);
		Object[] objects = c.toArray();
		int length = objects.length;
		resizeCapacity(countElement + length);
		int elementMoved = countElement - index;
		if (elementMoved > 0) {
			System.arraycopy(elements, index, elements, index + length, elementMoved);
		}
		System.arraycopy(objects, 0, elements, index, length);
		countElement += length;
		return length != 0;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		for (Object element : c) {
			if (element != null && contains(element)) {
				remove(element);
			}
		}
		return true;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		int index = 0;
		do {
			if (!c.contains(elements[index])) {
				remove(elements[index]);
				index--;
			}
			index++;
		} while (index < countElement);
		return true;
	}

	@Override
	public void clear() {
		for (int index = 0; index < countElement; index++) {
			elements[index] = null;
		}
		countElement = 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(int index) {
		checkIndex(index);
		return (T) elements[index];
	}

	@SuppressWarnings("unchecked")
	@Override
	public T set(int index, T element) {
		checkIndex(index);
		T oldElement = (T) elements[index];
		elements[index] = element;
		return oldElement;
	}

	@Override
	public void add(int index, T element) {
		checkIndex(index);
		resizeCapacity(1);
		System.arraycopy(elements, index, elements, index + 1, countElement - index);
		elements[index] = element;
		countElement++;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T remove(int index) {
		checkIndex(index);
		T oldElement = (T) elements[index];
		remove(oldElement);
		return oldElement;
	}

	@Override
	public int indexOf(Object o) {
		if (o == null) {
			for (int index = 0; index < countElement; index++) {
				if (elements[index] == null) {
					return index;
				}
			}
		} else {
			for (int index = 0; index < countElement; index++) {
				if (o.equals(elements[index])) {
					return index;
				}
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		if (o == null) {
			for (int index = countElement - 1; index >= 0; index--) {
				if (elements[index] == null) {
					return index;
				}
			}
		} else {
			for (int index = countElement - 1; index >= 0; index--) {
				if (o.equals(elements[index])) {
					return index;
				}
			}
		}
		return -1;
	}

	@Override
	public ListIterator<T> listIterator() {
		return new ListIter(0);
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		checkIndex(index);
		return new ListIter(index);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		if (fromIndex < 0) {
			throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
		}
		if (toIndex > countElement) {
			throw new IndexOutOfBoundsException("toIndex = " + toIndex);
		}
		if (fromIndex > toIndex) {
			throw new IllegalArgumentException("fromIndex(" + fromIndex +
					") > toIndex(" + toIndex + ")");
		}
		CustomList<T> newList = new CustomList<>();
		Object[] objects = Arrays.copyOfRange(elements, fromIndex, toIndex);
		newList.addAll((Collection<? extends T>) Arrays.asList(objects));
		return newList;
	}

	private void resizeCapacity(int length) {
		int availableCapacity = elements.length - countElement;
		int shift = elements.length >> 1;

		if (availableCapacity < shift) {
			int newCapacity = elements.length + shift;
			if (newCapacity - countElement < length) {
				newCapacity = length;
			}
			elements = Arrays.copyOf(elements, newCapacity);
		}
	}

	private void fastRemove(int index) {
		int length = countElement - index - 1;
		if (length > 0) {
			System.arraycopy(elements, index + 1, elements, index, length);
		}
		elements[--countElement] = null;
	}

	private void checkIndex(int index) {
		if (index > countElement || index < 0) {
			throw new IndexOutOfBoundsException(INDEX_IS_OUT_OF_RANGE);
		}
	}

	private class Iter implements Iterator<T> {
		int cursor;
		int lastRet = -1;

		@Override
		public boolean hasNext() {
			return cursor != countElement;
		}

		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			int index = cursor;
			if (index >= countElement) {
				throw new NoSuchElementException();
			}
			Object[] objects = CustomList.this.elements;
			if (index >= objects.length) {
				throw new ConcurrentModificationException();
			}
			cursor = index + 1;
			lastRet = index;
			return (T) objects[lastRet];
		}

		@Override
		public void remove() {
			if (lastRet < 0) {
				throw new IllegalStateException();
			}
			try {
				CustomList.this.remove(lastRet);
				cursor = lastRet;
				lastRet = -1;
			} catch (IndexOutOfBoundsException e) {
				throw new ConcurrentModificationException(e.getMessage(), e);
			}
		}
	}

	private class ListIter extends Iter implements ListIterator<T> {
		ListIter(int index) {
			super();
			cursor = index;
		}

		@Override
		public boolean hasPrevious() {
			return cursor != 0;
		}

		@SuppressWarnings("unchecked")
		@Override
		public T previous() {
			int index = cursor - 1;
			if (index < 0) {
				throw new NoSuchElementException();
			}
			Object[] objects = CustomList.this.elements;
			if (index >= objects.length) {
				throw new ConcurrentModificationException();
			}
			cursor = index;
			lastRet = index;
			return (T) objects[lastRet];
		}

		@Override
		public int nextIndex() {
			return cursor;
		}

		@Override
		public int previousIndex() {
			return cursor - 1;
		}

		@Override
		public void set(T t) {
			if (lastRet < 0) {
				throw new IllegalStateException();
			}
			try {
				CustomList.this.set(lastRet, t);
			} catch (IndexOutOfBoundsException e) {
				throw new ConcurrentModificationException(e.getMessage(), e);
			}
		}

		@Override
		public void add(T t) {
			try {
				int index = cursor;
				CustomList.this.add(index, t);
				cursor = index + 1;
				lastRet = -1;
			} catch (IndexOutOfBoundsException e) {
				throw new ConcurrentModificationException(e.getMessage(), e);
			}
		}
	}
}
