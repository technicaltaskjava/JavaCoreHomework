package Task4;

import java.io.*;
import java.util.*;

/**
 * Created by Oleg on 14.03.2016.
 */
public class MyFileReader {


    private Set<String> reservedWords = new HashSet<String>();

    public static void main(String[] args) {

        Set<String> wordsToFind = new HashSet<String>();
        wordsToFind.add("abstract");
        wordsToFind.add("continue");
        wordsToFind.add("for");
        wordsToFind.add("new");
        wordsToFind.add("switch");
        wordsToFind.add("assert");
        wordsToFind.add("default");
        wordsToFind.add("goto");
        wordsToFind.add("package");
        wordsToFind.add("synchronized");
        wordsToFind.add("boolean");
        wordsToFind.add("do");
        wordsToFind.add("if");
        wordsToFind.add("private");
        wordsToFind.add("this");
        wordsToFind.add("break");
        wordsToFind.add("double");
        wordsToFind.add("implements");
        wordsToFind.add("protected");
        wordsToFind.add("throw");
        wordsToFind.add("byte");
        wordsToFind.add("else");
        wordsToFind.add("import");
        wordsToFind.add("public");
        wordsToFind.add("throws");
        wordsToFind.add("case");
        wordsToFind.add("enum");
        wordsToFind.add("instanceof");
        wordsToFind.add("return");
        wordsToFind.add("transient");
        wordsToFind.add("catch");
        wordsToFind.add("extends");
        wordsToFind.add("int");
        wordsToFind.add("short");
        wordsToFind.add("try");
        wordsToFind.add("char");
        wordsToFind.add("final");
        wordsToFind.add("interface");
        wordsToFind.add("static");
        wordsToFind.add("void");
        wordsToFind.add("class");
        wordsToFind.add("finally");
        wordsToFind.add("long");
        wordsToFind.add("strictfp");
        wordsToFind.add("volatile");
        wordsToFind.add("const");
        wordsToFind.add("float");
        wordsToFind.add("native");
        wordsToFind.add("super");
        wordsToFind.add("while");
        wordsToFind.add("const");
        wordsToFind.add("and");
        wordsToFind.add("goto");


        checkFileForReservedWords(wordsToFind);
    }


    public static void checkFileForReservedWords(Set<String> wordsToFind) {


        try {

            FileWriter fileWriter = new FileWriter(WorkWithConsoleInput.enterPath());
            for (String string :
                    wordsToFind) {
                FileReader fileReader1 = new FileReader(WorkWithConsoleInput.enterPath());
                Scanner scanner = new Scanner(fileReader1);

                int count = 0;
                while (scanner.hasNextLine()) {
                    if (scanner.findInLine(string) != null) {
                        count++;
                    }
                    scanner.nextLine();
                }


                if (count > 0) {
                    fileWriter.write(string + " find " + count + " times\n");
                    fileWriter.flush();
                }
                fileReader1.close();

            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
    }

}