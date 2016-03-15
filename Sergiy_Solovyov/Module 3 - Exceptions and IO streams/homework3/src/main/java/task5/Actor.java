package task5;

import java.io.Serializable;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 12.03.2016
 */
public class Actor implements Serializable{
    private String surname;
    private String firstName;

    @Override
    public String toString() {
        return "Actor{" +
                "firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                "}";
    }

    public Actor(String firstName, String surname) {
        this.surname = surname;
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
