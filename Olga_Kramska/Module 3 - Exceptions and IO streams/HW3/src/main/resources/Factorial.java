import java.util.Scanner;

/**
 * Created by Olga Kramska on 11-Feb-16
 */

public class Factorial {

    public static void main(String[] args) {
        System.out.println("name: Olga Kramska");
        System.out.println("date: 12 February 2016");
        System.out.println("Enter an integer number:");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        if (n < 0) {
            System.out.println("wrong number");
        } else {
            System.out.println("The value of " + n + "! is");
            System.out.println(fact1(n) + " using a loop");
            System.out.println(fact2(n) + " using recursion");
        }
    }

    static int fact2(int n) {
        if (n < 2) {
            return 1;
        } else {
            return n * fact2(n - 1);
        }
    }

    static int fact1(int n) {
        int f = 1;
        if (n < 2) {
            f = 1;
        } else {
            for (int i = 2; i <= n; i++) {
                f *= i;
            }
        }
        return f;
    }
}

