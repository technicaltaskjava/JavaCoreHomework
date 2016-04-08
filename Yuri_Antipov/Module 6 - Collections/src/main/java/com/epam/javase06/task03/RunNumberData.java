package com.epam.javase06.task03;

public class RunNumberData {
    private RunNumberData(){}

    public static void main(String[] args) {
        NumberData data = new NumberData();
        data.addNumber(343);
        data.addNumber(51);
        data.addNumber(0.1);
        data.addNumber(-34.3009);
        data.toString();
        System.out.println();

        data.addNumberByIndex(2, 8844);
        data.toString();
        System.out.println();

        data.removeNumber(51);
        data.toString();
        System.out.println();

        data.removeNumberByIndex(3);
        data.toString();
        System.out.println();

        int anyNumber = 0;
        Number nearestNumber = data.searchNearestNumber(anyNumber);
        System.out.println("The nearest number to the number " + anyNumber + " is number " + nearestNumber);
    }
}
