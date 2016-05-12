package epam.com.task2;

public class CookieDTO {
    private int cookieId;
    private String cookie;

    public CookieDTO(String cookie, int cookieId) {
        this.cookie = cookie;
        this.cookieId = cookieId;
    }

    public int getCookieId() {
        return cookieId;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
}
