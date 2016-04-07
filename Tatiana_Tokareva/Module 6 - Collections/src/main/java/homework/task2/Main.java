package homework.task2;

public class Main {
    private Main() {
    }

    public static void main(String[] args) {

        InterfaceList interfaceList=new InterfaceList();
        System.out.println("Size of array is "+interfaceList.size());
        System.out.println("Array is empty: "+interfaceList.isEmpty());
        interfaceList.add(1);
        interfaceList.add(2);
        interfaceList.add(3);
        interfaceList.add(3,4);
        interfaceList.add(4,5);
        interfaceList.set(5,6);
        System.out.println("Array after add 1 2 3 4 5 6: "+interfaceList);
        System.out.println("Array is empty: "+interfaceList.isEmpty());
        interfaceList.remove(2);
        System.out.println("After removed from position 2 :"+interfaceList);

        System.out.println("position of number 9 is "+interfaceList.indexOf(9));



    }
}
