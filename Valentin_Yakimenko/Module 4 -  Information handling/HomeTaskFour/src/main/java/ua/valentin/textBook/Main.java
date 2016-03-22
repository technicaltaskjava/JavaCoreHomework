package ua.valentin.textBook;

/**
 *
 * Created by valentin.yakimenko on 22.03.16.
 */
public class Main {
    public static void main(String[] args) {
        Book book = new Book("stl.txt", "cp1251");
        System.out.println(book.toStringAsCollection());
    }
 }
