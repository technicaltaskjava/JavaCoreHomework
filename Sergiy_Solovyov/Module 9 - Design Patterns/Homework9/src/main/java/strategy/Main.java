package strategy;

/**
 * @author Sergey Solovyov
 */
public class Main {

    public static final String TEXT = "Samme0062@gmail.com  063-696-56-96 " +
                                "theaugustprime@gmail.com  +38(095)569-96-96 " +
                                "y.krishtop@gmail.com  096-696-69-33";
    private Main(){}

    public static void main(String[] args) {
        Finder p = new Finder(new WordsFinder());
        System.out.println(p.parseText(TEXT));;
        p.setParser(new PhoneNumberFinder());
        System.out.println(p.parseText(TEXT));;
    }
}
