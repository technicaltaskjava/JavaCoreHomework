package task3.elements;

public class Unit<A>
    {
        private A first;

        private Unit(A first)
            {
                this.first = first;

            }

        public static <A> Unit<A> creat(A first)
            {
                return new Unit<A>(first);
            }

        public  Unit getUnit()
            {
                return creat(first);

            }
        public A getFirst()
            {
                return first;
            }

        @Override
        public boolean equals(Object obj) {
            if (obj==this)
                return  true;
            Unit unit = (Unit) obj;
            if (unit.getFirst() == this.getFirst())
                return true;
            return false;
        }


        @Override
        public String toString()
            {
                return "[" + first + "]";
            }



    }


