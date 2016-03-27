package ua.valentin.textBook;

/**
 * Model of Book.
 * Created by valentin.yakimenko on 22.03.16.
 */
public class Book {
    private String filePath;
    private String textEncoding;
    private Line lines;

    public Book(String filePath, String textEncoding) {
        this.filePath = filePath;
        this.textEncoding = textEncoding;
        this.lines = new Line("Book");
        lines.addLines(Parser.getLinesFromBook(this));
    }

    public String getFilePath() {
        return filePath;
    }

    public String getTextEncoding() {
        return textEncoding;
    }

    public String toStringAsCollection() {
        return lines.getLines();
    }

    @Override
    public String toString() {
        return lines.getLinesAsString();
    }
 }
