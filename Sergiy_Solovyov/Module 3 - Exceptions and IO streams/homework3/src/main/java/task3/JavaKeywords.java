package task3;

import java.util.Collections;
import java.util.HashSet;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 11.03.2016
 */
public class JavaKeywords {

   public static HashSet<String> keywords = new HashSet<String>();

    static {
        Collections.addAll(keywords,
                ("abstract continue for new switch assert default goto package synchronized " +
                        "boolean tdo if private this break double implements protected throw " +
                        "FilmsCollection else import public throws " +
                        "case enum instanceof return transient " +
                        "catch extends int short try " +
                        "char final interface static void " +
                        "class finally long strictfp volatile " +
                        "const float native super while").split(" "));
           }
    }

