package strategy;

/**
 * @author Sergey Solovyov
 */
public class Finder {

    private TextParser parser;

    public Finder(TextParser parser){
        this.parser = parser;
    }

    public  String parseText(String text){
      return parser.parse(text);
    }

    public void setParser(TextParser parser) {
        this.parser = parser;
    }
}

