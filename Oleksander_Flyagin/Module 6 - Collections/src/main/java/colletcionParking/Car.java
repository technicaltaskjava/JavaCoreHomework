package colletcionParking;

public class Car
    {
        private String model;
        private String driverName;
        private int place;

        public Car(String driverName, String model)
            {
                this.driverName = driverName;
                this.model = model;
            }

        public String getModel()
            {

                return model;
            }



        public String getDriverName()
            {
                return driverName;
            }

        public void goToRight()
            {
                System.out.println("go to right");
            }


        public int getPlace()
            {
                return place;
            }

        public void setPlace(int place)
            {
                this.place = place;
            }


        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Car other = (Car) obj;
            if (getModel() != other.getModel())
                return false;
            if (getModel() != other.getModel())
                return false;
            return true;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + Integer.parseInt(getModel());
            return result;
        }

        @Override
        public String toString()
            {
                return "[Driver " + getDriverName() + " Model " + getModel() + "]";
            }

        public String showInfo()
            {
                return "[Driver " + getDriverName() + " Model " + getModel() + " Plasce " + getPlace()+ "]";
            }
    }
