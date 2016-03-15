package traks;

public abstract class Rock implements Traks
    {
        private final String stayle = "Rock";
        private String nameTrak;
        private String actor;
        private double duration;

        public Rock(String nameTrak, String actor, double duration)
            {
                this.nameTrak = nameTrak;
                this.actor = actor;
                this.duration = duration;

            }

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
        public abstract int[] getSattingsEqualize();

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
                boolean res = true;

                if (this == obj)
                    {
                        res = true;
                    }
                if (obj == null)
                    {
                        res = false;
                    }
                if (getClass() != obj.getClass())
                    {
                        res = false;
                    }
                Traks other = (Traks) obj;
                if (getSattingsEqualize() != other.getSattingsEqualize())
                    {
                        res = false;
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


