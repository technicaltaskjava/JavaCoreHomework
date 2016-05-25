package dao.substance;


public class Cookie {

    private int id;
    private String cookie;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    @Override
    public String toString() {
        return "Cookie{" +
                "id=" + id +
                ", cookie='" + cookie + '\'' +
                '}';
    }
}
