package elements;

public class Speaker {
    private String name;
    private int lines;
    private int words;

    public void setName(String name) {
        this.name = name;
    }

    public void setLines(int lines) {
        this.lines = lines;
    }

    public void setWords(int words) {
        this.words = words;
    }

    public String getName() {
        return name;
    }

    public int getLines() {
        return lines;
    }

    public int getWords() {
        return words;
    }

    @Override
    public boolean equals(Object next){ //NOSONAR
        return next != null && Integer.compare(this.hashCode(), next.hashCode()) == 0;
    }

    @Override
    public int hashCode(){
        return name.hashCode();
    }

    @Override
    public String toString(){
        return "Speaker: " + name + ", lines = " + lines + ", average words per line = " + (float)words/lines;
    }
}
