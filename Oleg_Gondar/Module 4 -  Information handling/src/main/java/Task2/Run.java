package Task2;

/**
 * Created by O.Gondar on 22.03.2016.
 */
public class Run {

    public static void main(String[] args) {

        CrazyLogger crazyLogger = new CrazyLogger();
        crazyLogger.addLog("test1");
        crazyLogger.addLog("test2");
        crazyLogger.addLog("test3");

        crazyLogger.logToSTDOut();
        crazyLogger.logToFileWriter("test.txt");

        System.out.println(crazyLogger.getLog(".*t2"));

    }


}
