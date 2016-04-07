package homework.task3;


public class Main {
    private Main() {
    }

    public static void main(String[] args) {

        StorageOfNumber data = new StorageOfNumber();
        data.add(4.7);
        data.add(4.4);
        data.add(4.0);

        System.out.println("List after added elements" + data.getData());


        System.out.println("number close to 4.1 is " + data.find(4.1));
        System.out.println("number close to 9 is " + data.find(9.0));

        double wrongNumber = 2.8;
        boolean isDel = data.delete(wrongNumber);
        showDeleteResult(isDel, wrongNumber);
        double correctNumber = 4.4;
        isDel = data.delete(correctNumber);
        showDeleteResult(isDel, correctNumber);
        System.out.println("List after deleted" + data.getData());


    }

    private static void showDeleteResult(boolean isDel, double number) {
        if (isDel) {
            System.out.println(String.format("%s is delete", number));
        } else {
            System.out.println(String.format("Can not delete %s", number));
        }
    }
}
