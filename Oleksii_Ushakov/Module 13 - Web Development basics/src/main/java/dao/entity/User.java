package dao.entity;

import java.sql.Date;
import java.util.Calendar;

/**
 * @author Alexey Ushakov
 */
public class User {
    private final int id;
    private String login;
    private String firstName;
    private String secondName;
    private String password;
    private String email;
    private Date regDate;

    public User() {
        this.id = -1;
        this.login = "login";
        this.firstName = "firstName";
        this.secondName = "secondName";
        this.email = "unknown@email.com";
        this.regDate = new Date(Calendar.getInstance().getTime().getTime());
    }

    public User(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        return id == user.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
