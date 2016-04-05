package com.epam.task1.candy;

/**
 * Created by Olga Kramska on 04-Mar-16.
 */
public abstract class Candy {
    protected double weight;
    protected String name;

    public Candy(double weight, String name) {
        this.weight = weight;
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Candy)) {
            return false;
        }

        Candy candy = (Candy) o;

        if (Double.compare(candy.getWeight(), getWeight()) != 0) {
            return false;
        }
        return getName().equals(candy.getName());

    }

    // There is no necessity to override hashCode in Candy, but Sonar requires for it.
    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(getWeight());
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + getName().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return name + " " + weight + " g";
    }
}
