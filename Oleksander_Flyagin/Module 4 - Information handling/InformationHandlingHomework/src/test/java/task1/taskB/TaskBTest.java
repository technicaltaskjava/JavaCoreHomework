package task1.taskB;

import org.junit.Assert;

public class TaskBTest
    {
        TaskB splt = new TaskB();

        @org.junit.Test
        public void testShiftMathEnter() throws Exception
            {

            }
        @org.junit.Test
        public void testSortWords() throws Exception
            {
                String text = "Шиншила Шина Дождь Гром Свет в 12";
                String [] exp  =  new  String[] {"Шина", "Шиншила", "Гром", "Свет", "Дождь" };
                String [] resoult = splt.sortWords(text);
                Assert.assertArrayEquals(resoult,exp);            }



        @org.junit.Test
        public void testSplitOnWords() throws Exception
            {

               String [] resoult = splt.splitOnWords("Вася черный Вася кот Вася");
                String [] exp  =  new  String[] {"Вася", "черный", "кот"};
                Assert.assertArrayEquals(resoult,exp);
                Assert.assertEquals(3, resoult.length);


            }

        @org.junit.Test
        public void testDellPunctuation() throws Exception
            {

                String resoult = splt.dellPunctuation("Вася, кот. черный");
                String exp = "Вася  кот  черный";
                Assert.assertEquals(resoult, exp);


            }

        @org.junit.Test
        public void testSplitDubleWords() throws Exception
            {
                String resoult = splt.splitDubleWords("Вася черный Вася кот Вася");
                String exp = "Вася черный кот";
                Assert.assertEquals(resoult, exp);

            }
        @org.junit.Test
        public void testSum() throws Exception
            {
                String str = "РадиоАэроНавигация";
                int res = splt.sumVowels(str);
                int exp = 11;
                Assert.assertEquals(res, exp);

            }
        @org.junit.Test
        public void testSum1() throws Exception
            {
                String str = "в";
                int res =   splt.sumVowels(str);
                int exp = 0;
                Assert.assertEquals(res,exp);

            }






    }