package cd;

import traks.Traks;

import java.math.BigDecimal;
import java.util.Iterator;

public class CD   implements Iterable<Traks>
    {
        private Traks[] treks = new Traks[16];
        private int top = 0;


        public CD(Traks[] ini)
            {
                init(ini);
            }

        public CD()
            {

            }

        public int size()
            {
                return top;
            }


        public void init(Traks[] ini)
            {
                if (ini == null)
                    {
                        for (int i = 0; i < top; i++)
                            {
                                treks[i] = ini[i];
                            }
                    }

                for (int i = 0; i < ini.length; i++)
                    {
                        treks[i] = ini[i];
                    }
                top = ini.length;
            }


        public Traks[] toArray()
            {
                if (size() == 0)
                    {
                        throw new NullPointerException();
                    }

                Traks[] tmp = new Traks[top];
                for (int i = 0; i < top; i++)
                    {
                        tmp[i] = treks[i];
                    }
                return tmp;
            }

        public Traks getWithCD(int pos)
            {
                return  treks[pos];
            }


        public void setOnCD(int pos, Traks trek)
            {
                treks[pos] = trek;
            }


        public double durationCD()
            {
                double duration;
                double time =0;
                for (int i = 0; i < top; i++)
                    {
                        time+=treks[i].getDuration();
                    }
                duration = BigDecimal.valueOf(time).setScale(2,BigDecimal.ROUND_HALF_DOWN).doubleValue() ;
                return duration ;
            }



        public Iterator<Traks> iterator()
            {
                return new MIterator();
            }


        class MIterator implements Iterator<Traks>
            {
                int i = 0;

                @Override
                public boolean hasNext()
                    {
                        return i < size();
                    }

                @Override
                public Traks next()
                    {

                        return (Traks) treks[i++];
                    }
            }


    }
