package car;

/**
 * @author Alexey Ushakov
 */
public class Car {
    private static final String NUMBER_PATTERN = "[А-Я]{2}[\\d]{4}[А-Я]{2}";
    private String regionCode;
    private int carNumberDigits;
    private String series;

    public Car(String number) {
        String checkedNumber = number.replaceAll("\\s", "");
        if (checkedNumber.matches(NUMBER_PATTERN)) {
            regionCode = checkedNumber.substring(0, 2);
            carNumberDigits = Integer.valueOf(checkedNumber.substring(2, 6));
            series = checkedNumber.substring(6, 8);
        } else {
            throw new IllegalArgumentException("Wrong number " + number);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Car carNumber = (Car) o;

        if (this.carNumberDigits != carNumber.carNumberDigits) {
            return false;
        }

        if (!regionCode.equals(carNumber.regionCode)) {
            return false;
        }

        return series.equals(carNumber.series);

    }

    @Override
    public int hashCode() {
        int result = regionCode.hashCode();
        result = 31 * result + carNumberDigits;
        result = 31 * result + series.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return regionCode.concat(" ").concat(String.valueOf(carNumberDigits)).concat(" ").concat(series);
    }
}
