package task5;

import messages.Message;

import java.io.*;
import java.io.Console;
import java.util.ArrayList;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 12.03.2016
 */
public class Main {

    private static Message m = new Message();

    public static void main(String[] args) {
        FilmsApplication filmsApplication = new FilmsApplication();
        ArrayList<Film> films =  filmsApplication.readFile("byte");
        filmsApplication.listFilmsMenu(films);

        m.message("Save the collection of films and exit the program - \"exit\"\n" +
                  "          To create new film - \"create\"");
        while (true){

            films = filmsApplication.filmMenuEdit(films);
            filmsApplication.listFilmsMenu(films);
            m.message("Save the collection of films and exit the program - \"exit\"\n" +
                    "          To create new film - \"create\"");}

    }

}
