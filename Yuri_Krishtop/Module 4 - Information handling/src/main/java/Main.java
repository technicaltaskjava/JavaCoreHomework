import com.epam.task1texttransformer.TextTransformer;
import com.epam.task1texttransformer.WordComparer;
import com.epam.task2logger.CrazyLogger;
import com.epam.task3regexp.FindSentenceWithImageLink;

import java.util.Arrays;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {


        String line = "+-----------------------------------------------------------------------------------------------------------+";
        System.out.println(line);
        System.out.println("|  Home Work 04: Information handling                                                                       |");
        System.out.println(line);
        System.out.println("|  Please, enter an integer number for start:                                                               |");
        System.out.println("|  Task1:                                                                                                   |");
        System.out.println("|    1 - replace first and last words in sentences                                                          |");
        System.out.println("|    2 - sorting words in order of increasing portion of vowel letters                                      |");
        System.out.println("|    3 - deleting all words from text, which start with consonants as the first letter                      |");
        System.out.println("|    4 - words transformation by deleting of first letter on following positions                            |");
        System.out.println("|  Task2:                                                                                                   |");
        System.out.println("|    5 - start CrazyLogger                                                                                  |");
        System.out.println("|  Task3:                                                                                                   |");
        System.out.println("|    6 - print sentences, which contain references on picture                                               |");
        System.out.println(line);
        System.out.println("|    7 - close                                                                                              |");
        System.out.println(line);
        Scanner input = new Scanner(System.in);

        if (input.hasNextInt()) {
            int idOperation = input.nextInt();
            switch (idOperation) {
                case 1:
                    TextTransformer myTextTransformer = new TextTransformer("./src/main/java/com/epam/task1texttransformer/java.txt");
                    System.out.println(line);
                    System.out.println("Before replacing:");
                    System.out.println(line);
                    for (String s : myTextTransformer.getSentences(myTextTransformer.getText())) {
                        System.out.println(s);
                    }
                    System.out.println(line);
                    System.out.println("After replacing:");
                    System.out.println(line);
                    System.out.println(myTextTransformer.replaceWordsInSentens(myTextTransformer.getSentences(myTextTransformer.getText())));
                    break;
                case 2:
                    TextTransformer myTextTransformer2 = new TextTransformer("./src/main/java/com/epam/task1texttransformer/java.txt");
                    String[] words = myTextTransformer2.getWords(myTextTransformer2.getSentences(myTextTransformer2.getText()));
                    System.out.println(line);
                    System.out.println("Before sorting:");
                    System.out.println(line);
                    int countChar = 0;
                    for (String w : words) {
                        System.out.print(w + ", ");
                        countChar += w.length();
                        if (countChar > 100) {
                            countChar = 0;
                            System.out.println();
                        }
                    }
                    System.out.println();
                    System.out.println(line);
                    System.out.println("After sorting:");
                    System.out.println(line);
                    WordComparer[] wordsForSorting = new WordComparer[words.length];
                    for (int i = 0; i < words.length; i++) {
                        wordsForSorting[i] = new WordComparer(words[i]);
                    }
                    Arrays.sort(wordsForSorting);
                    countChar = 0;
                    for (WordComparer w : wordsForSorting) {
                        System.out.print(w + ", ");
                        countChar += w.getWord().length();
                        if (countChar > 100) {
                            countChar = 0;
                            System.out.println();
                        }
                    }
                    break;
                case 3:
                    System.out.println("Please, enter positive integer for deleting words from text:");
                    Scanner in = new Scanner(System.in);
                    if (in.hasNextInt()) {
                        int countLetter = in.nextInt();
                        if (countLetter < 0) {
                            countLetter = -countLetter;
                        } else {
                            TextTransformer myTextTransformer3 = new TextTransformer("./src/main/java/com/epam/task1texttransformer/java.txt");
                            System.out.println(line);
                            System.out.println("Before deleting:");
                            System.out.println(line);
                            System.out.println(myTextTransformer3.getText());
                            System.out.println(line);
                            System.out.println("After deleting:");
                            System.out.println(line);
                            System.out.println(myTextTransformer3.deleteWords(myTextTransformer3.getText(), countLetter));
                        }
                    }
                    break;
                case 4:
                    TextTransformer myTextTransformer4 = new TextTransformer("./src/main/java/com/epam/task1texttransformer/java.txt");
                    System.out.println(line);
                    System.out.println("|  Text before deleting first letter on next position in word:                                              |");
                    System.out.println(line);
                    System.out.println(myTextTransformer4.getText());
                    System.out.println(line);
                    System.out.println("|   Text after deleting first letter on next position in word:                                              |");
                    System.out.println(line);
                    String text = TextTransformer.deleteLettersFromWord(myTextTransformer4.getText());
                    String[] sentences = text.split("\\. ");
                    for (String s : sentences) {
                        System.out.println(s);
                    }
                    break;
                case 5:
                    System.out.println(line);
                    System.out.println("| Hello! This is CrazyLogger!                                                                               |");
                    System.out.println(line);
                    System.out.println("| Choose, what do you want:  1 - create logger with output on  console                                      |");
                    System.out.println("|                            2 - create logger with output in file                                          |");
                    System.out.println("|                            3 - find messages in file                                                      |");
                    System.out.println(line);
                    Scanner inpt = new Scanner(System.in);

                    if (inpt.hasNextInt()) {
                        int idOpn = inpt.nextInt();
                        switch (idOpn) {
                            case 1:
                                CrazyLogger myLogOnConsole = new CrazyLogger();
                                System.out.println(line);
                                System.out.println("| Please, enter some text:                                                                                  |");
                                System.out.println(line);
                                Scanner inp = new Scanner(System.in);
                                String textForLoger = inp.nextLine();
                                myLogOnConsole.addLog(textForLoger);
                                break;
                            case 2:
                                CrazyLogger myLogInFile = new CrazyLogger("log.txt");
                                System.out.println(line);
                                System.out.println("| Please, enter some text:                                                                                  |");
                                System.out.println(line);
                                Scanner scanin = new Scanner(System.in);
                                String textForFile = scanin.nextLine();
                                myLogInFile.addLog(textForFile);
                                break;
                            case 3:
                                CrazyLogger myLogInFileLog = new CrazyLogger("log.txt");
                                System.out.println(line);
                                System.out.println("| Please, enter text for finding:                                                                           |");
                                System.out.println(line);
                                Scanner scaner = new Scanner(System.in);
                                String textForFind = scaner.nextLine();
                                System.out.println(line);
                                System.out.println("| In log was found:                                                                                         |");
                                System.out.println(myLogInFileLog.findLog(textForFind));
                                break;
                            default:
                                System.out.println("Command did not found");
                        }

                    } else {
                        System.out.println("Please, enter integer in range 1 - 3");
                    }
                    break;
                case 6:
                    FindSentenceWithImageLink myFind = new FindSentenceWithImageLink(
                            "./src/main/java/com/epam/task3regexp/Information handling_task_attachment.html");
                    System.out.println(line);
                    System.out.println("| There are  sentences, which contain references on picture:                                                |");
                    System.out.println(line);
                    myFind.getSentencesWithImageLink(myFind.getSentences(myFind.getText()));
                    break;
                case 7:
                    System.exit(0);
                default:
                    System.out.println("Command did not found");

            }


        } else {
            System.out.println("Sorry, you have to enter integer.");
        }

    }

}
