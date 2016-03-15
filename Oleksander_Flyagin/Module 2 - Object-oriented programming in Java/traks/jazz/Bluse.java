package traks.jazz;

import traks.Jazz;

public class Bluse extends Jazz
    {

        public Bluse(String nameTrak, String actor, double duration)
            {
                super(nameTrak, actor, duration);
            }
        private int treble =  70 ;
        private int mids   =  15 ;
        private int low    = -27 ;

        private int [] equalize = new int[3];


        @Override
        public int [] getSattingsEqualize()
            {
                equalize[0] = treble;
                equalize[1] = mids  ;
                equalize[2] = low   ;

                return      equalize;


            }

    }
