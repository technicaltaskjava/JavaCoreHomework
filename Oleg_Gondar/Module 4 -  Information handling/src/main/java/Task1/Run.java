package Task1;

import java.util.List;

/**
 * Created by Oleg on 20.03.2016.
 */
public class Run {

    public static void main(String[] args) {

        String s = SentenceUtilites.swapWordsInSentences();
        SentenceUtilites.sortWordsInText();
        List<String> wordsAfterRemove = SentenceUtilites.removeWords(3);
        System.out.println("Words after removing: ");
        for (String string :
                wordsAfterRemove) {
            System.out.println(string);
        }

        SentenceUtilites.replaceLetters();


    }

}
