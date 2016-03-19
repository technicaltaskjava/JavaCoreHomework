package Task3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Oleg on 15.03.2016.
 */
public class FindInLine {

    public static int find(String stringInWichFind, String wordToFind) {

        String[] words = stringInWichFind.split("\\W");


        int count = 0;
        for (int i = 0; i < words.length; i++) {
            Scanner sc = new Scanner(words[i]);
            if (sc.findInLine(wordToFind) != null) {
                count++;
            }


        }

        return count;

    }

}


