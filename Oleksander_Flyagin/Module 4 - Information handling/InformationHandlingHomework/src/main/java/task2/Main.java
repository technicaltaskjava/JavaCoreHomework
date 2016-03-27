package task2;

public class Main
    {
        public static void main(String[] args)
            {

               Calk calk = new Calk();
                calk.calcMenu();
               System.out.println(calk.logger.getMassage());
                System.out.println("-------------------------------------");
                calk.logger.grep();

            }

    }
