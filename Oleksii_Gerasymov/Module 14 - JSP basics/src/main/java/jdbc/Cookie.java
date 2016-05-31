package jdbc;

public class Cookie {
    private int id;
    private String cookieName;
    private boolean active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCookieName() {
        return cookieName;
    }

    public void setCookieName(String cookie) {
        this.cookieName = cookie;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
