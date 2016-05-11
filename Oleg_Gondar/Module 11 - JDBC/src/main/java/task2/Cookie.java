package task2;

/**
 * Created by Oleg on 11.05.2016.
 */
public class Cookie {
    private int Cookie_Id;
    private String Cookie;

    public Cookie(String cookie, int cookie_Id) {
        Cookie = cookie;
        Cookie_Id = cookie_Id;
    }

    public int getCookie_Id() {
        return Cookie_Id;
    }

    public void setCookie_Id(int cookie_Id) {
        Cookie_Id = cookie_Id;
    }

    public String getCookie() {
        return Cookie;
    }

    public void setCookie(String cookie) {
        Cookie = cookie;
    }
}
