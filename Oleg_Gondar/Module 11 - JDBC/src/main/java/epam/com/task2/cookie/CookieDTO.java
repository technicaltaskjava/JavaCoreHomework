package epam.com.task2.cookie;

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

    public void setCookieId(int cookieId) {
        this.cookieId = cookieId;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
}
