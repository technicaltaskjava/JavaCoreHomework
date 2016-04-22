package number;

/**
 * @author Alexey Ushakov
 */
public class PrimeNumber {
    private PrimeNumber() {
    }

    public static boolean isPrimeNumber(int number) {
        if (number > 1) {
            for (int i = 2; i <= Math.sqrt(number); i++) {
                if (number % i == 0) {
                    return false;
                }
            }
        } else {
            return false;
        }

        return true;
    }

}
