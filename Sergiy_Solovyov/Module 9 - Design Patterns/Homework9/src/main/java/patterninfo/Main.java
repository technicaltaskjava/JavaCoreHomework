package patterninfo;

/**
 * @author Sergey Solovyov
 */
public class Main {

    private Main(){}

    public static void main(String[] args) {
        PatternInfo info = new PatternInfo();
        info.readFromFile("src/main/resources/PatternInfo.txt");
    }
}
