package colletcionParking;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Parking implements Iterable<Car>
    {
        Car car;

        Car [] place = new Car[20];

        public int size()
            {
                return place.length;
            }


        public  void goOut(String name, String model)
            {
                int placaeNuber = 0;

                for (Car places : place)
                    {
                        if (places.getModel().equals(model) && places.getDriverName().equals(name))
                            {
                                break;
                            }
                            placaeNuber++;
                    }
                System.out.println("Car " + place[placaeNuber] + " go out");
                place[placaeNuber] = null;

            }

        public void goToPlace(Car car, int placeNuber)
            {

                if (place[placeNuber] != null)
                    System.out.println("This place is busy");
                place[placeNuber] = car;
                System.out.println("Car " + place[placeNuber].toString() + " go to place " + placeNuber);


            }

        public void getToPlace(int placeNuber)
            {
                if (place[placeNuber] == null)
                        System.out.println("This place is free");

                System.out.println("Car " + place[placeNuber].toString() + " go out  ");
                place[placeNuber] = null;

            }

        public void goTo(Car car)
            {


                if (sumEmptyPlace()== 0)
                    {
                        System.out.println("Park don't have empty place");
                    }

                System.out.println("Car  " + car + " serch place");
                for (int placaeNuber = 0; placaeNuber<size(); placaeNuber++)
                    {

                        if(place[placaeNuber]==null)
                            {
                                place[placaeNuber] = car;
                                place[placaeNuber].setPlace(placaeNuber);
                                System.out.println("Car found place " + placaeNuber);
                                break;
                            }
                        car.goToRight();


                    }
            }

        public void getInfoForPlace()
            {
                System.out.println("Buzy places "  + sumBusyPlace());
                System.out.println("Free places "  + sumEmptyPlace());
                for (Car places : place)
                    {
                        if (places != null)
                            {
                                System.out.println(places.showInfo());
                            }
                    }
            }

        public int sumEmptyPlace()
            {
                int sum = 0;
                for (Car places : place)
                    {
                        if(places==null)
                            sum++;
                    }
                return sum;
            }

        public int sumBusyPlace()
            {
                int sum = 0;
                for (Car places : place)
                    {
                        if(places!=null)
                            sum++;
                    }
                return sum;

            }


        @Override
        public Iterator<Car> iterator()
            {
                return (Iterator<Car>) new CarItecrator(place);
            }




        class CarItecrator implements Iterator<Car>
            {
                Car [] array = null;
                int stap = 0;

                public CarItecrator(Car[] place)
                    {
                        this.array = place;
                    }

                @Override
                public boolean hasNext()
                    {
                        return stap < size();
                    }

                @Override
                public Car next()
                    {
                        if(!hasNext()){
                            throw new NoSuchElementException();
                        }

                        return place[stap++];
                    }
            }


    }

