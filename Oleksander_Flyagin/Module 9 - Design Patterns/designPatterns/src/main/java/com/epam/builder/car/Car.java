package com.epam.builder.car;

public class Car {


    private Car() {
    }

    public Car(String manufacturer, String model, String typeCar, int issue, String state, int price) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.typeCar = typeCar;
        this.issue = issue;
        this.state = state;
        this.price = price;
    }

    private  String manufacturer;
    private  String model;
    private  String typeCar;
    private  int    issue;
    private  String state;
    private  int    price;



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
                "; typeCar "  + typeCar +
                "; issue " + issue +
                "; state " + state +
                "; price " + price +
                " ]";
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj == this)
            return true;

        if(obj == null)
            return false;

        if(!(getClass() == obj.getClass()))
            return false;
        else
        {
            Car tmp = (Car) obj;
            if(tmp.getManufacturer() == this.getManufacturer() || tmp.getModel() == this.getModel())
                return true;
            else
                return false;
        }
    }
    @Override
    public int hashCode()
    {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + getModel().length()*13;
        return result;
    }




}
