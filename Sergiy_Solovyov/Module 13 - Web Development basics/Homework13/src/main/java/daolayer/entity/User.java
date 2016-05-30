package daolayer.entity;

/**
 * @author Sergey Solovyov
 */
public class User {

    private int id;
    private String userName;
    private String email;
    private String passw;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return passw;
    }

    public void setPassword(String passw) {
        this.passw = passw;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", passw='" + passw + '\'' +
                ", age=" + age +
                '}';
    }
}
