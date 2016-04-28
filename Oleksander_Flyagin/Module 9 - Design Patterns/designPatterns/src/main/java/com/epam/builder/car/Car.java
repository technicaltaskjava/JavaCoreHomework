package com.epam.builder.car;

public class Car {

    private String manufacturer;

    private String model;
    private String typeCar;
    private int issue;
    private String state;
    private int price;
    public Car(String manufacturer, String model, String typeCar, int issue, String state, int price) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.typeCar = typeCar;
        this.issue = issue;
        this.state = state;
        this.price = price;
    }




    private Car() {
    }





    public int getPrice() {
        return price;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public String getTypeCar() {
        return typeCar;
    }

    public int getIssue() {
        return issue;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return "[ manufacturer " + manufacturer +
                "; model " + model +
                "; typeCar " + typeCar +
                "; issue " + issue +
                "; state " + state +
                "; price " + price +
                " ]";
    }

    @Override
    public boolean equals(Object obj) {
        boolean expression = false;
        if (obj == this) {
            return !!expression;
        }

        if (obj == null) {
            return expression;
        }

        if (!(getClass() == obj.getClass())) {
            return expression;
        } else {
            Car tmp = (Car) obj;
            if (tmp.getManufacturer() == this.getManufacturer() || tmp.getModel() == this.getModel()) {
                return !!expression;
            } else {
                return expression;
            }
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + getModel().length() * 13;
        return result;
    }


}
