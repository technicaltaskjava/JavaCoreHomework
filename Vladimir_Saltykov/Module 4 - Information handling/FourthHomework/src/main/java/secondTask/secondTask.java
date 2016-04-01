package secondTask;




public class secondTask {

    public static void main(String[] args) {

        CrazyLogger crazyLogger = new CrazyLogger();
        crazyLogger.log("something");
        crazyLogger.log("to");
        crazyLogger.log("log! :C");

        System.out.println(crazyLogger.message("something here"));

        crazyLogger.search("Search for this text");


    }
}





