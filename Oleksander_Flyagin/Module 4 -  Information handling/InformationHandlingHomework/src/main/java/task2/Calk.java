package task2;

import java.util.Scanner;

public class Calk
    {

       // StringBuilder saveLog = new StringBuilder();
        CrazyLogger logger = new CrazyLogger();
        Scanner text = new Scanner(System.in);

        public void calcMenu()
            {
                logger.write("STATUS START OPERATION");
                int a;
                int b;
                System.out.println("Start program");
                System.out.println("Select operation:\r\nsum     - enter \"sum\"\r\ndivide  - enter \"div\"\r\n" +
                        "mult    - enter \"mult\"\r\nsubract - enter \"sub\"");
                String resoult = text.next();
                Scanner nubers = new Scanner(System.in);
                System.out.println("Enter  first nuber");
                a = Integer.parseInt(nubers.next());
                System.out.println("Enter  next nuber");
                b = Integer.parseInt(nubers.next());


                switch (resoult)
                    {
                        case "sum":System.out.println(  sum (a, b));
                            break;
                        case "div":System.out.println(  div (a, b));
                            break;
                        case "mult":System.out.println( mult(a, b));
                            break;
                        case  "sub":System.out.println( sub (a, b));
                            break;
                        default:
                        {
                            System.out.println("Your operation isn't correct. Try again");
                            logger.write("STATUS ERROR DON'T COMPLIT");



                        }
                    }

               System.out.println("Do you carry out operation again? (\"yes\" \\ \"no\")");
               Scanner ansver = new Scanner(System.in);

                switch (ansver.next())
                    {
                        case "yes": calcMenu();
                            break;
                        case "y"  : calcMenu();
                            break;
                       default    :  logger.write("STATUS EXIT");
                            break;

                    }



            }



        private int sum(int a, int b)
            {
                logger.write("STATUS START SUMMATION OPERATION");
                int resoult;
                resoult = a+b;
                logger.write(" STATUS COMPLETED SUMMATION OPERATION SUCCESSFUL");
                return resoult;
            }
        private double div(int a, int b)
            {
                double resoult=0;
                logger.write("STATUS START DIVIDE OPERATION");
                if(b==0)
                    {
                        System.out.println("ERROR\r\nYou can't divinding on \"0\"");
                        logger.write("STATUS ERROR DON'T COMPLIT OPERATION OF DIVIDE");



                    }
                else
                    {
                        resoult = (double) a / b;
                        logger.write("STATUS COMPLETED DIVIDE OPERATION SUCCESSFUL");


                    }
                return resoult;
            }

        private int mult(int a, int b)
            {
                int resoult=0;
                logger.write("STATUS START MILTIPLICATION OPERATION");

                resoult = a * b;
                logger.write("STATUS COMPLETED MILTIPLICATION OPERATION SUCCESSFUL");

                return resoult;
            }
        private int sub(int a, int b)
            {
                int resoult=0;
                logger.write("STATUS START SUBTRACT OPERATION");
                resoult = a - b;
                logger.write("STATUS COMPLETED SUBTRACT OPERATION SUCCESSFUL");
                return resoult;
            }


    }
