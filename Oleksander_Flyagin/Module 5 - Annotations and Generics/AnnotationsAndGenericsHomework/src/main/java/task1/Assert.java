package task1;

public class Assert
    {
        public static void assertEquals(int exp, int resoult)
            {
                if (exp == resoult)
                    {
                        System.out.println("Resoult is true");
                    }
                else
                    {
                        System.out.println("Resoult is false");
                    }
            }

        public static void assertEquals(double exp, double resoult)
            {
                if (exp == resoult)
                    {
                        System.out.println("Resoult is true");
                    }
                else
                    {
                        System.out.println("Resoult is false");
                    }
            }

        public static void assertEquals(String exp, String resoult)
            {

                        if (exp.equals(resoult))
                            {
                                System.out.println("Resoult is true");
                            }
                        else
                            {
                                System.out.println("Resoult is false");
                            }

            }
    }
