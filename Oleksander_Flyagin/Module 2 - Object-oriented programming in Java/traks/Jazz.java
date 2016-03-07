package traks;

public abstract class Jazz implements Traks
    {

        private final String stayle = "Jazz";

        private String nameTrak;
        private String actor;
        private double duration;

        public Jazz(String nameTrak, String actor, double duration)
            {
                this.nameTrak = nameTrak;
                this.actor = actor;
                this.duration = duration;
            }

        public abstract int[] getSattingsEqualize();


        @Override
        public String getNameTrak()
            {
                return nameTrak;
            }



        @Override
        public String getActor()
            {
                return actor;
            }



        @Override
        public String getStayle()
            {
                return stayle;
            }

        @Override
        public double getDuration()
            {
                return duration;
            }

        @Override
        public String toString()
            {
                String toString ="[ Trak's name : " + this.nameTrak + "; "+ "actors : " + this.actor + "; " +
                        "stayle : " + this.stayle + "; " + "duration : " + this.duration + " ]";

                return   toString;
            }

        @Override
        public boolean equals(Object obj)
            {

                if (this == obj)

                    {
                        return true;
                    }


                if (obj == null)


                    {
                        return false;
                    }


                if (getClass() != obj.getClass())


                    {
                        return false;
                    }


                Traks other = (Traks) obj;

                boolean res = true;

                for (int index = 0 ; index<getSattingsEqualize().length; index++)
                    {
                        if (getSattingsEqualize()[index] != other.getSattingsEqualize()[index])
                            {
                                res =false;
                                break;
                            }


                    }
                return res;



            }

        @Override
        public int compareTo(Object obj)
            {
                Traks traks = (Traks) obj;
                if(this.getDuration() < traks.getDuration())
                    {
                        return -1;
                    }
                else if(this.getDuration() > traks.getDuration())
                    {
                        return 1;
                    }

                return 0;
            }

    }
