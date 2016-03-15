package Task3;

import java.io.*;
import java.util.*;

/**
 * Created by Oleg on 13.03.2016.
 */
public class MyByteFileReader {


    public static void main(String[] args) {


        Set<String> wordsToFind = new HashSet<>();
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


        String s = null;
        try {
            s = ReadFile.readStringFromFile(WorkWithConsoleInput.enterPath());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        OutputStream os = null;
        try {
            os = new FileOutputStream(WorkWithConsoleInput.enterPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PrintStream printStream = null;
        if (os != null) {
            printStream = new PrintStream(os);
        }

        int i;


        for (String string :
                wordsToFind) {
            i = FindInLine.find(s, string);
            if (i > 0) {
                writeStringToFile(printStream, string + " find " + i + " times\n");
            }
        }
        if (printStream != null) {
            printStream.close();
        }
    }


    public static void writeStringToFile(PrintStream printStream, String textToWrite) {

        printStream.print(textToWrite);

    }

}





