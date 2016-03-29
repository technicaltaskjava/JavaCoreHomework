package task3.elements;

public class Pair<A, B>

    {
        private A first;
        private B second;

        private Pair(A first, B second)
            {
                this.first = first;
                this.second = second;
            }


        public static <A, B> Pair<A, B> creat(A first, B second)
            {
                return new Pair<A, B>(first, second);
            }

        public  Pair getPair()
            {

                return creat(first, second);

            }
        public A getFirst()
            {

                return first;
            }
        public B getSecond()
            {
                return second;
            }

        @Override
        public String toString()
            {
                return "[" + first + ", " + second + "]";

            }

       @Override
        public boolean equals(Object obj) {
            if (obj==this)
                return  true;
           Pair pair = (Pair) obj;
           if (pair.getFirst() == this.getFirst() || pair.getSecond() == this.getSecond())
               return true;
           return false;
        }


    }
