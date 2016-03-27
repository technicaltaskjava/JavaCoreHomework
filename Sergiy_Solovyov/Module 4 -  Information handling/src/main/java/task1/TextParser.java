package task1;

import console.Console;
import console.WrongCharException;

import java.io.FileNotFoundException;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 16.03.2016
 */
public class TextParser {

    private LetterRemover letterRemover = new LetterRemover();
    private WordRemover wordRemover = new WordRemover();
    private VowelsProportionSorter sorter = new VowelsProportionSorter();
    private WordReplacer replacer = new WordReplacer();
    private Console console = new Console();

    public void run() throws FileNotFoundException, WrongCharException {
        System.out.println("Replace first and last word in text - 1");
        System.out.println("Sort all words by vowels proportion - 2");
        System.out.println("Remove words by first letter and length - 3");
        System.out.println("Remove next first letter in words   - 4");
        System.out.println("Exit program enter any number except 1, 2, 3, 4");
        switch (console.intInput()) {
            case 1:
                System.out.println(replacer.readFile("Text.txt"));;
                break;
            case 2:
                System.out.println(sorter.readFile("textTask_1.txt"));
                break;
            case 3:
                char firstLetter = 0;
                System.out.println("Enter first letter of the word you want to delete");
                firstLetter = console.charInput();
                int wordLength = 0;
                System.out.println("Enter word length");
                wordLength = console.intInput();
                System.out.println(wordRemover.readFile("textTask_1.txt", firstLetter,wordLength));;
                break;
            case 4:
                System.out.println(letterRemover.readAndEditFile("textTask_1.txt"));;
                break;
            default:
                System.exit(0);
    }

    }

}
