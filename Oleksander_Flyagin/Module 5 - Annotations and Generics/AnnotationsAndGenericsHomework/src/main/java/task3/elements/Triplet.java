package task3.elements;

public class Triplet<A, B, C>
    {

        private A first;
        private B second;
        private C third;



        private Triplet(A first, B second, C third)
            {
                this.first = first;
                this.second = second;
                this.third = third;

            }


        public static <A, B, C> Triplet<A, B, C> creat(A first, B second, C third)
            {
                return new Triplet<A, B, C>(first, second, third);
            }
        public A getFirst()
        {
            return first;
        }
        public B getSecond()
            {
                return second;
            }
        public C getThird()
            {
                return third;
            }

        public  Triplet getTriplet()
            {
                return  creat(first, second, third);
            }

        @Override
        public boolean equals(Object obj) {
            if (obj==this)
                return  true;
            Triplet triplet = (Triplet) obj;
            if (triplet.getFirst() == this.getFirst() || triplet.getSecond() == this.getSecond() || triplet.getThird() == this.getThird() )
                return true;
            return false;
        }

        @Override
        public String toString()
            {
                return "[" + first + ", " + second + ", " + third + "]";

            }


    }
