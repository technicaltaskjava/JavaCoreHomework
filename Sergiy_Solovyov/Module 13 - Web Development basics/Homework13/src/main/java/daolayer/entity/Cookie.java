package daolayer.entity;

/**
 * @author Sergey Solovyov
 */
public class Cookie {

    private int id;
    private String cookieText;

    public String getCookieText() {
        return cookieText;
    }

    public void setCookie(String cookie) {
        this.cookieText = cookie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Cookie{" +
                "id='" + id + '\'' +
                ", cookieText=" + cookieText +
                '}';
    }
}
