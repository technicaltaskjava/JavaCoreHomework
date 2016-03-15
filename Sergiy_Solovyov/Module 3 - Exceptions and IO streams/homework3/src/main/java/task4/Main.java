package task4;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 11.03.2016
 */
public class Main {
    public static void main(String[] args) {
        ReadJavaCode readJavaCode = new ReadJavaCode();
        readJavaCode.createAndWriteFile(readJavaCode.mapToString(readJavaCode.readFile("testcode.txt")), "Keywords2.txt");
    }
}
