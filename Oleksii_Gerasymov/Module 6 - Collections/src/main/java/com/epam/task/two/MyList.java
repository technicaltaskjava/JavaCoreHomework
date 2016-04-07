package com.epam.task.two;

public class MyList<T> {
    T[] currentList;

    public MyList() {
        currentList = (T[]) new Object[0];
    }

    public int getListLength() {
        return this.currentList.length;
    }

    public String getList() {
        String resultString = "";
        for (int indexArray = 0; indexArray < this.currentList.length; indexArray++) {
            resultString += currentList[indexArray] + ", ";
        }
        return resultString;
    }

    public T[] getSubList(int begin, int end) {
        T[] newList = (T[]) new Object[end - begin + 1];
        System.arraycopy(this.currentList, begin-1, newList, 0, end - begin + 1);
        return newList;
    }

    public T getElementByIndex(int position) {
        return (T) this.currentList[position-1];
    }

    public int getIndexByElement(T newElement) {
        for (int itemIndex = 0; itemIndex < this.currentList.length; itemIndex++) {
            if (newElement.equals(this.currentList[itemIndex])) {
                return itemIndex+1;
            }
        }
        return -1;
    }

    public void addElementToList(T newElement) {
        T[] newList = (T[]) new Object[this.currentList.length+1];
        System.arraycopy(this.currentList, 0, newList, 0, this.currentList.length);
        newList[this.currentList.length] = newElement;
        this.currentList = (T[]) new Object[newList.length];
        System.arraycopy(newList, 0, this.currentList, 0, this.currentList.length);
    }

    public void insertElementToList(T newElement, int position) {
        T[] newList = (T[]) new Object[this.currentList.length + 1];
        System.arraycopy(this.currentList, 0, newList, 0, position - 1);
        newList[position-1] = newElement;
        System.arraycopy(this.currentList, position - 1, newList, position, newList.length - position);
        this.currentList = (T[]) new Object[newList.length];
        System.arraycopy(newList, 0, this.currentList, 0, this.currentList.length);
    }

    public void changeElementByIndex(T newElement, int position) {
        this.currentList[position-1] = newElement;
    }

    public void removeElementByIndex(int position) {
        T[] newList = (T[]) new Object[this.currentList.length - 1];
        System.arraycopy(this.currentList, 0, newList, 0, position - 1);
        System.arraycopy(this.currentList, position, newList, position-1, this.currentList.length - position);
        this.currentList = (T[]) new Object[newList.length];
        System.arraycopy(newList, 0, this.currentList, 0, this.currentList.length);
    }
}
