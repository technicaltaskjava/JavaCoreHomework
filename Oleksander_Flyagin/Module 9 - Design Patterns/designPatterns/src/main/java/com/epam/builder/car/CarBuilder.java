package com.epam.builder.car;

public class CarBuilder {


    private String manufacturer;
    private String model;
    private String typeCar = "passenger car";
    private int    issue;
    private String state = "new";
    private int    price;

    public CarBuilder setPrice(int price) {
        this.price = price;
        return this;
    }


    public CarBuilder setManufacturer(final String  manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public CarBuilder setModel(final String model) {
        this.model = model;
        return this;
    }

    public CarBuilder setTypeCar(final String typeCar) {
        this.typeCar = typeCar;
        return this;
    }

    public CarBuilder setIssue(final int issue) {
        this.issue = issue;
        return this;
    }

    public CarBuilder setState(final String state) {
        this.state = state;
        return this;
    }

    public Car build()
    {
        return new Car(manufacturer, model, typeCar, issue, state, price);
    }


}
