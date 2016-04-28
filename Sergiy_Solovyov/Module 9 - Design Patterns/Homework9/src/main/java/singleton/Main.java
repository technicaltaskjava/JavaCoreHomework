package singleton;

/**
 * @author Sergey Solovyov
 */
public class Main {

    private Main(){}

    public static void main(String[] args) {
        DBInfo info = DBInfo.getInstance();
        System.out.println(info);
    }
}
