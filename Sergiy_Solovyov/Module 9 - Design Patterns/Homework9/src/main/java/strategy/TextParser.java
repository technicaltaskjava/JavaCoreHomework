package strategy;

/**
 * @author Sergey Solovyov
 */
//Sonar wants @FunctionalInterface annotation
public interface TextParser { //NOSONAR

    public String parse(String text);
}
