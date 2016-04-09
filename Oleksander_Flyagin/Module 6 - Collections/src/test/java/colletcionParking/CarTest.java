package colletcionParking;

import org.junit.Assert;

public class CarTest
    {

        @org.junit.Test
        public void testEquals()
            {
                Car car = new Car("Bob" , "BMW");
                Car car1 = new Car("Den" , "BMW");
                Assert.assertEquals(car.equals(car1), false);

            }
        @org.junit.Test
        public void testEquals1()
            {
                Car car = new Car("Bob" , "BMW");
                Car car1 = new Car("Bob" , "BMW");
                Assert.assertEquals(car.equals(car1), true);

            }
    }