package servlets;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Yuriy Krishtop on 22.05.2016.
 */
public class Validate {

    private static final String LOGIN_PATTERN = "^[a-zA-Z0-9_-]{3,20}$";
    private static final String PAS_PATTERN = "^[a-zA-Z0-9]{4,20}$";
    private static final String EMAIL_PATTERN = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
    private static Pattern pattern;
    private static Matcher matcher;

    private Validate() {
    }

    public static boolean isLoginValid(String login) {
        pattern = Pattern.compile(LOGIN_PATTERN);
        matcher = pattern.matcher(login);
        return matcher.matches();
    }

    public static boolean isPasValid(String pas) {
        pattern = Pattern.compile(PAS_PATTERN);
        matcher = pattern.matcher(pas);
        return matcher.matches();
    }

    public static boolean isEmailValid(String email) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
