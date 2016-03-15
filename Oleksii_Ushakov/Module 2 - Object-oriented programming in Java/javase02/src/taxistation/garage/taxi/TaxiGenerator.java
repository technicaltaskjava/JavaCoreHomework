package taxistation.garage.taxi;

import java.util.Random;

/**
 * @author Alexey Ushakov
 */
public class TaxiGenerator {
    private static Class[] childes = {TaxiCoupe.class, TaxiHatchback.class, TaxiSedan.class};
    private static Random random = new Random();

    public static Taxi next() {
        try {
            return (Taxi) childes[random.nextInt(childes.length)].newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
