package taxistation;

import taxistation.garage.taxi.Taxi;
import taxistation.garage.taxi.TaxiCoupe;
import taxistation.garage.taxi.TaxiGenerator;
import taxistation.garage.taxi.TaxiSedan;
import taxistation.garage.passengercar.Car;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Alexey Ushakov
 */


public class TaxiStation {
    private Taxi[] carArr;

    public TaxiStation() {
        Random random = new Random();
        carArr = new Taxi[15 + new Random().nextInt(15)];
        for (int i = 0; i < carArr.length; i++) {
            carArr[i] = TaxiGenerator.next();
            if (carArr[i] instanceof TaxiCoupe) {
                carArr[i].setPrice(15000 + random.nextInt(10) * 100);
                carArr[i].setFuelConsumption(10 + random.nextInt(5));
            } else if (carArr[i] instanceof TaxiSedan) {
                carArr[i].setPrice(25000 + random.nextInt(10) * 100);
                carArr[i].setFuelConsumption(17 + random.nextInt(5));
            } else {
                carArr[i].setPrice(20000 + random.nextInt(10) * 100);
                carArr[i].setFuelConsumption(13 + random.nextInt(5));
            }
        }
    }

    public Car getCar(int index) {
        if (isIndex(index))
            return carArr[index];
        throw new ArrayIndexOutOfBoundsException("Индекс должен быть в диапазоне [0:" + carArr.length + "]");
    }

    public Taxi[] getCarAll() {
        return carArr;
    }

    public Taxi[] searchTaxiByPrice(int priceToSearch) {
        int searchCount = 0;
        int[] searchIndexes = new int[carArr.length];

        for (int i = 0; i < carArr.length; i++) {
            if (carArr[i].getPrice() == priceToSearch) {
                searchIndexes[searchCount++] = i;
            }
        }

        Taxi[] result = new Taxi[searchCount];
        for (int i = 0; i < searchCount; i++) {
            result[i] = carArr[searchIndexes[i]];
        }

        return result;
    }

    private boolean isIndex(int index) {
        return (index < 0 && index >= carArr.length);
    }

    public int getCarParkCost() {
        int carParkCost = 0;
        for (Car car : carArr)
            carParkCost += car.getPrice();
        return carParkCost;
    }

    public void sortByFuelConsumption() {
        Arrays.sort(carArr);
    }
}
