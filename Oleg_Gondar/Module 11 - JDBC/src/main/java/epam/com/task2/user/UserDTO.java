package epam.com.task2.user;

public class UserDTO {

    private int userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userLastName;
    private String userFirstName;
    private String address;
    private String sity;
    private String country;

    public UserDTO() {
        sity = "Dnepropetrovsk";
        country = "Ukraine";
    }

    public UserDTO(int userId, String userName, String userPassword, String email) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = email;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public String getAddress() {
        return address;
    }

    public String getSity() {
        return sity;
    }

    public String getCountry() {
        return country;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSity(String sity) {
        this.sity = sity;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
