package com.epam.task.two;

public class MyListRun {

    private MyListRun() {
    }

    public static void mainMenu() {
        MyList testList = new MyList();
        testList.addElementToList("First");
        testList.addElementToList("Second");
        testList.addElementToList("Third");
        testList.addElementToList("Fourth");
        printList(testList);

        testList.addElementToList("Fifth");
        System.out.println("Element Fifth added.");
        printList(testList);

        testList.insertElementToList("Six",4);
        System.out.println("Element Six added to position 4.");
        printList(testList);

        testList.changeElementByIndex("Seven",3);
        System.out.println("Element Seven added to position 3.");
        printList(testList);

        testList.removeElementByIndex(3);
        System.out.println("Element at position 3 removed.");
        printList(testList);

        System.out.println("Element at position 2 is : " + testList.getElementByIndex(2));
        printList(testList);

        if (testList.getIndexByElement("Fourth") != -1) {
            System.out.println("Element third is at position : " + testList.getIndexByElement("Third"));
            }
            else {
                System.out.println("Element not founded. ");
            }
        printList(testList);

        Object[] subList = testList.getSubList(2,4);
        System.out.println("Length of sublist is : " + subList.length);
        String resultString = "";
        for (int indexArray = 0; indexArray < subList.length; indexArray++) {
            resultString += subList[indexArray] + ", ";
        }
        System.out.println("Current sublist is : " + resultString);
    }

    private static void printList(MyList testList) {
        System.out.println("Length of list is : " + testList.getListLength());
        System.out.println("Current list is : " + testList.getList());
        System.out.println();
    }
}
