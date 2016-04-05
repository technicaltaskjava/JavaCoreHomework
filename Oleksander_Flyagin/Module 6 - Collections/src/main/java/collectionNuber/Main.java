package collectionNuber;

public class Main
    {
        private Main()
            {
            }

        public static void main(String[] args)
            {

                NuberDateMyList nuberDate = new NuberDateMyList();
                nuberDate.addNumber(12);
                nuberDate.addNumber(12.5);
                nuberDate.addNumber(11.5);
                nuberDate.addNumber(12.15);
                nuberDate.addNumber(1.4);
                nuberDate.addNumber(5.8);
                nuberDate.addNumber(7.4);

                nuberDate.showDate();
                nuberDate.removeNuber(7.4);

                nuberDate.showDate();
                nuberDate.sercNumber();






            }
    }
