package traks.rock;

import traks.Rock;

public class PostRock extends Rock
    {

        public PostRock(String nameTrak, String actor, double duration)
            {
                super(nameTrak, actor, duration);
            }

        private int treble =  60 ;
        private int mids   =  25 ;
        private int low    = -70 ;

        private int [] equalize = new int[3];

        @Override
        public int [] getSattingsEqualize()
        {
        equalize[0] = treble;
        equalize[1] = mids;
        equalize[2] = low;

        return equalize;
        }

    }
