package dao.entity;

import java.sql.Date;

/**
 * @author Alexey Ushakov
 */
public class Cookie {
    private final int id;
    private String divination;
    private Date expirationDate;
    private double price;

    public Cookie(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDivination() {
        return divination;
    }

    public void setDivination(String divination) {
        this.divination = divination;
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

        Cookie cookie = (Cookie) o;

        return id == cookie.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
