package traks.rock;

import traks.Rock;

public class AlternativeRock extends Rock
    {

        public AlternativeRock(String nameTrak, String actor, double duration)
            {
                super(nameTrak, actor, duration);
            }


        private int treble =  45 ;
        private int mids   =  25 ;
        private int low    = -19 ;

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
