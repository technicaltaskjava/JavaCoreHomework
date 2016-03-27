package messages;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 12.03.2016
 */
public class Message {

    public final static String separator = "------------------------------------------------------------------------";
    public final static String info = "[INFO] ";
    public final static String warn = "[WARNING] ";
    public final static String mess = "[MESSAGE] ";

    public void warn(String message){
        System.out.println(separator);
        System.out.print(warn);
        System.out.println(message);
        System.out.println(separator);
    }
    public void info(String message){
        System.out.println(separator);
        System.out.print(info);
        System.out.println(message);
        System.out.println(separator);
    }
    public void message(String message){
        System.out.println(separator);
        System.out.print(mess);
        System.out.println(message);
        System.out.println(separator);
    }
}
