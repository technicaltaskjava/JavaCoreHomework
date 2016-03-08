package RailwayRollingStock.ManagementUnits;

import RailwayRollingStock.Carriages.Passengers.CommonPassengerCarriage;
import RailwayRollingStock.Carriages.Passengers.LongDistancePassengerCarriage;
import RailwayRollingStock.Carriages.CarriageType;
import RailwayRollingStock.Interfaces.PassengerTrainStuffCount;
import RailwayRollingStock.Locomotives.PassengerLocomotive;


public class PassengerTrain extends Train implements PassengerTrainStuffCount {

    protected CommonPassengerCarriage[] carriages;

    public PassengerTrain() {
        this.carriages = new CommonPassengerCarriage[0];
    }

    @Override
    public int getPassengerCount() {
        int summ = 0;
        for (CommonPassengerCarriage c : carriages) {
            summ += c.getPassengersCount();
        }
        return summ;
    }

    @Override
    public int getBaggageCount() {
        int summ = 0;
        for (CommonPassengerCarriage c : carriages) {
            summ += c.getBaggageCount();
        }
        return summ;
    }

    @Override
    public void addLocomotive() {

        locomotive = new PassengerLocomotive();

    }

    public CommonPassengerCarriage getCarriages(int indexOfCarriage) {
        return carriages[indexOfCarriage];
    }

    public CommonPassengerCarriage[] getCarriages() {
        return carriages;
    }

    public void setCarriages(CommonPassengerCarriage[] carriages) {
        this.carriages = carriages;
    }

    @Override
    public void attachWagon(CarriageType carriageType) {
        carriages = increaseMassiveForTrainWagons();
        if (carriageType == CarriageType.COMMON) {
            carriages[carriages.length - 1] = new CommonPassengerCarriage();
        } else {
            carriages[carriages.length - 1] = new LongDistancePassengerCarriage(carriageType);
        }
    }

    @Override
    public void detachWagon() {
        this.decreaseMassiveForTrainWagons();
    }

    public CommonPassengerCarriage[] increaseMassiveForTrainWagons() {
        CommonPassengerCarriage[] tempMassive = new CommonPassengerCarriage[carriages.length + 1];
        if (this.carriages.length != 0) {
            System.arraycopy(this.carriages, 0, tempMassive, 0, this.carriages.length);
        }
        return tempMassive;

    }

    public CommonPassengerCarriage[] decreaseMassiveForTrainWagons() {
        if (this.carriages.length <= 1) {
            return new CommonPassengerCarriage[0];
        } else {
            CommonPassengerCarriage[] tempMassive = new CommonPassengerCarriage[this.carriages.length - 1];
            System.arraycopy(this.carriages, 0, tempMassive, 0, this.carriages.length - 1);
            return tempMassive;
        }
    }

    public void showAllCarriageTypes() {
        for (int i = 0; i < carriages.length; i++) {
            System.out.println(carriages[i].getCarriageType());
        }
    }

    public void carriagesSort() throws CloneNotSupportedException {
        CommonPassengerCarriage[] tempMassive = new CommonPassengerCarriage[carriages.length];
        int sortedMassIndex = 0;
        for (CarriageType carriageType : CarriageType.values()) {
            for (int i = 0; i < carriages.length; i++) {
                if (carriageType.equals(carriages[i].getCarriageType())) {
                    tempMassive[sortedMassIndex] = (CommonPassengerCarriage) carriages[i].clone();
                    sortedMassIndex++;

                }

            }
        }
        carriages = tempMassive;
    }


}





