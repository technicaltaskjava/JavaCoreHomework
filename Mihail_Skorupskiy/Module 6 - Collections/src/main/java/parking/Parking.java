package parking;

public class Parking {

    private Car[] buffer;

    private Parking(){}

    public Parking(int size){
        buffer = new Car[size];
    }

    public int size(){
        return buffer.length;
    }

    public int freeSpotsAmount(){
        int spots = 0;
        for (int i = 0; i < buffer.length; i++){
            if (buffer[i] == null){
                spots++;
            }
        }
        return spots;
    }

    public int[] freeSpotsPositions(){
        int[] temp = new int[freeSpotsAmount()];
        int tempIndex = 0;
        for (int i = 0; i < buffer.length; i++){
            if (buffer[i] == null){
                temp[tempIndex] = i;
                tempIndex++;
            }
        }
        return temp;
    }

    public boolean placeCar(Car car, int position){
        if(!car.isParked() && position >= 0 && position < buffer.length) {
            for (int i = position; i < buffer.length; i++) {
                if (buffer[i] == null) {
                    buffer[i] = car;
                    car.setParked(true);
                    break;
                }
            }
        }
        return car.isParked();
    }

    public boolean removeCar(Car car){
        if (car.isParked()) {
            for (int i = 0; i < buffer.length; i++) {
                if (buffer[i] == car) {
                    buffer[i] = null;
                    car.setParked(false);
                }
            }
        }
        return car.isParked();
    }

    public Car getCar(int position){
        return buffer[position];
    }

    public int findCar(Car car){
        for (int i = 0; i < buffer.length; i++){
            if (buffer[i] == car){
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString(){
        StringBuilder temp = new StringBuilder("\n");
        for (int i = 0; i < buffer.length; i++){
            if (buffer[i] != null){
                temp.append(buffer[i]);
                temp.append(" on position ");
                temp.append(i);
                temp.append(",\n");
            }
        }
        return temp.toString();
    }
}
