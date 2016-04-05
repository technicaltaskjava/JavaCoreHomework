package colletcionParking;

public class Main
    {
        private Main()
        {
        }
        public static void main(String[] args)
            {
                Parking park = new Parking();

                park.goTo(new Car("Bob","Mersedes"));
                park.goTo(new Car("Kent","BMW"));
                park.goTo(new Car("Sara","Jaguar"));
                park.getInfoForPlace();
                park.getToPlace(2);
                park.goToPlace(new Car("Sara","Jaguar") ,2);
                park.goOut("Kent","BMW");

            }
    }
