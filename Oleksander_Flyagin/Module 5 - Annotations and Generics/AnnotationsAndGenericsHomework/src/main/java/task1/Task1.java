package task1;


public class Task1
    {


        private int sum(int first, int second)
            {
                return first+second;
            }
        private double div(int first, int second)
            {
                if(second==0)
                    throw  new ArithmeticException();
                return first/second;
            }
        private String test(String test)
            {
                return test;
            }



        @MyTest(Ignore = true)
        public void testSum()
            {
                int resoult = sum(5,7);
                int exp     = 12;
                Assert.assertEquals(exp,resoult);

            }



        @MyTest
        public void testSumNext()
            {
                int resoult = sum(5,7);
                int exp     = 10;
                Assert.assertEquals(exp,resoult);

            }


        @MyTest
        public void testString()
            {
                String resoult = test("Test");
                String exp     = "Test";
                Assert.assertEquals(exp,resoult);
            }


       @MyTest(expected = java.lang.ArithmeticException.class)
        public void testDiv()
            {
                double resoult = div(12,0);
                double exp     = 6;
                Assert.assertEquals(exp, resoult);
            }




    }
