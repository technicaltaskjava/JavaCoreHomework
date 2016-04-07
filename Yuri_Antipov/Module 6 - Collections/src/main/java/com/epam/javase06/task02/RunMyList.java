package com.epam.javase06.task02;

public class RunMyList {
    private RunMyList(){}

    public static void main(String[] args) {
        MyList list = new MyList<String>(25);
        list.add("a new red car");
        list.add("a huge flat it London");
        list.add("a big salary");
        System.out.println(list.size());
        list.toString();
    }
}
