package listInterface;


import colletcionParking.Car;
import oopInJava.vegetables.Vegetables;
import oopInJava.vegetables.cabbage.Cabbage;

public class Test
    {
        private Test()
            {
            }

        public static void main(String[] args)
            {
                String test1 = "hi";
                String test2 = "Hellow";
                Vegetables vegetables = new Cabbage(12);
                Car car = new Car("Bob", "BMW");

                MyList array = new MyList();

                array.add(test1);
                array.add(test2);
                array.add(vegetables);
                array.add(car);

                System.out.println("Array1 size " + array.size());
                for (Object ar: array)
                    {
                        System.out.println(ar);
                    }
                array.remove(vegetables);
                System.out.println("Array1 size " + array.size());
                for (Object ar: array)
                    {
                        System.out.println(ar);
                    }

                MyList array2 = new MyList();
                array2.addAll(array);
                System.out.println("Array2 size " + array2.size());
                for (Object ar: array2)
                    {
                        System.out.println(ar);
                    }
                System.out.println("Conteins car" +  array.contains(car));
                System.out.println("Index car"+array.indexOf(car));

                array.remove(test1);

                for (Object ar: array2)
                    {
                        System.out.println("objects in Arry2 " + ar);
                    }
                array2.removeAll(array);

                System.out.println("Array2 size" + array2.size());
                System.out.println("Array1  size" +array.size());
                for (Object ar: array)
                    {
                        System.out.println("Delet objects " + ar);
                    }
                for (Object ar: array2)
                    {
                        System.out.println("Objects left " + ar);
                    }


            }


    }
