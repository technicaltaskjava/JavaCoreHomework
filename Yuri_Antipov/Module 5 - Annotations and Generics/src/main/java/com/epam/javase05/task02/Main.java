package com.epam.javase05.task02;

public class Main {
    public static void main(String[] args) {
        String[] names = {"Ivan", "Maria", "Vladimir", "Lev"};
        String minOfNames = CompareUtils.min(names);
        String maxOfNames = CompareUtils.max(names);
        String medOfNames = CompareUtils.median(names);
        System.out.println("minimum = " + minOfNames + ", maximum = " + maxOfNames + ", middle = " + medOfNames + ".");

        Integer[] numbers = {94, 384, 861, 975, 79, 84, 246};
        int minInt = CompareUtils.min(numbers);
        int maxInt = CompareUtils.max(numbers);
        int medInt = CompareUtils.median(numbers);
        System.out.println("minimum = " + minInt + ", maximum = " + maxInt + ", median = " + medInt + ".");
    }
}
