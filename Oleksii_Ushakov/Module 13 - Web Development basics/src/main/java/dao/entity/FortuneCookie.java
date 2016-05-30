package dao.entity;

import java.sql.Date;

/**
 * @author Alexey Ushakov
 */
public class FortuneCookie {
    private final int id;
    private String predication;
    private Date expirationDate;
    private double price;

    public FortuneCookie(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getPredication() {
        return predication;
    }

    public void setPredication(String predication) {
        this.predication = predication;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FortuneCookie fortuneCookie = (FortuneCookie) o;

        return id == fortuneCookie.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
