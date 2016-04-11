package javase.t01.play;

/**
 * Tag names enums for Play Parsers
 * Created by Yury Vislobodsky on 06.04.2016.
 */
public enum TagNames {
    SPEECH, SPEAKER, LINE, STAGEDIR, OTHER;

    public static TagNames getElementTagName(String element) {
        switch (element) {
            case "SPEECH":
                return SPEECH;
            case "SPEAKER":
                return SPEAKER;
            case "LINE":
                return LINE;
            case "STAGEDIR":
                return STAGEDIR;
            default:
                return OTHER;
        }
    }
}
