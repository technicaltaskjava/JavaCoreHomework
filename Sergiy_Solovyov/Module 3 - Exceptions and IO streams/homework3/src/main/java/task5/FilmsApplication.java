package task5;

import console.Console;
import messages.Message;

import java.io.*;
import java.util.ArrayList;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 12.03.2016
 */
public class FilmsApplication {

    private Console  console = new Console();
    private Message m = new Message();

    public ArrayList<Film> filmMenuEdit(ArrayList<Film> films){

        String string = console.stringInput();
        int filmMenu = 0;
        if (string.equalsIgnoreCase("exit"))
        {   writeFile(films, "FilmsCollection");
            System.out.println("Bye-bye");
            System.exit(0);}
        if (string.equalsIgnoreCase("create"))
        {films.add(createFilm());
            return films;}
        if (string.equalsIgnoreCase("-0"))
        {films.remove(0);
            return films;}
        else {
            try {
                filmMenu = Integer.valueOf(string);
                if (filmMenu < 0 && films.size() > Math.abs(filmMenu)) {
                    films.remove(Math.abs(filmMenu));
                    return films;

                }
                if (films.size() > filmMenu && filmMenu >= 0) {
                    FilmsApplication filmsApplication = new FilmsApplication();
                    filmsApplication.renameFilm(films.get(filmMenu));
                    ArrayList<Actor> actors = films.get(filmMenu).getActors();
                    filmMenu(actors);
                    m.message("To add new actor enter \"actor\"");
                    m.message("To return to collection enter any letter");
                    String actor = console.stringInput();
                    if (actor.equalsIgnoreCase("actor")) {
                        actors = addActors(actors);
                        films.get(filmMenu).setActors(actors);
                        return films;
                    }
                    if (actor.equalsIgnoreCase("-0")) {
                        actors.remove(0);
                        return films;
                    }
                    int act = Integer.parseInt(actor);
                    if (act < 0 && Math.abs(act) < actors.size()) {
                        actors.remove(Math.abs(act));
                    }
                    if (act >= 0 && act < actors.size()) {
                        m.message("Enter first name of actor and press enter");
                        String actorName = console.stringInput();
                        m.message("Enter surname of actor and press enter");
                        String actorSurname = console.stringInput();
                        actors.get(act).setFirstName(actorName);
                        actors.get(act).setSurname(actorSurname);
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                m.warn("*******Wrong input******");
            }
            catch (NumberFormatException e) {
                m.warn("*******Wrong input******");
            }
        }
        return films;
    }
    private  Film createFilm(){
        m.message("Enter name of the film and press enter");
        String filmName = console.stringInput();
        Film film = new Film(filmName);
        ArrayList<Actor> actors = new ArrayList<>();
        boolean flag = true;
        while (true) {
            m.info("Do you want to add actors? y/n");
            switch (console.stringInput()) {
                case "y":
                    m.message("Enter first name of actor and press enter");
                    String actorName = console.stringInput();
                    m.message("Enter surname of actor and press enter");
                    String actorSurname = console.stringInput();
                    Actor actor = new Actor(actorName, actorSurname);
                    actors.add(actor);
                    film.setActors(actors);
                    break;
                case "n":
                    return film;
                default: continue;
            }
        }
    }
    private  ArrayList<Actor> addActors(ArrayList<Actor> actors){


        while (true) {
            m.message("Enter first name of actor and press enter");
            String actorName = console.stringInput();
            m.message("Enter surname of actor and press enter");
            String actorSurname = console.stringInput();
            Actor actor = new Actor(actorName, actorSurname);
            actors.add(actor);
            m.message("Do you want do add another actor? y/n");
            switch (console.stringInput()) {
                case "y":
                    continue;
                case "n":
                    return actors;
                default: continue;
            }
        }
    }
    private  Film renameFilm(Film film){
        while (true){
            m.message("Do you want do rename film? y/n");
            switch (console.stringInput()) {
                case "y":
                    m.message("Enter new name of the film");
                    film.setName(console.stringInput());
                    m.info("Film renamed successfully");
                case "n":
                    return film;
                default: continue;
            }
        }
    }

    public void listFilmsMenu(ArrayList<Film> films){
        for (int i = 0; i < films.size(); i++){
            System.out.println("Press:  \""+ i +"\" to edit film\n"+"Press: \"-"+
                    i +"\" to delete film\n"+ films.get(i));
            System.out.println(Message.separator);

        }
    }
    private void filmMenu(ArrayList<Actor> actors){
        for (int i = 0; i < actors.size(); i++){
            System.out.println("Press: \""+ i +"\" to edit\n"+"Press: \"-"+ i +"\" to delete\n" + actors.get(i));
            System.out.println(Message.separator);
        }
    }

    public  ArrayList<Film> readFile(String name){
        ArrayList<Film> films1 = new ArrayList<>();
        try (ObjectInputStream ob = new ObjectInputStream(new BufferedInputStream(new FileInputStream(name)));){
            try {
                Object objRead =  ob.readObject() ;
                films1 = (ArrayList<Film>) objRead;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return films1;
    }
    private void writeFile(ArrayList<Film> films, String fileName){

        File file = new File(fileName);

        try (ObjectOutputStream ob = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));){
            ob.writeObject(films);
            ob.flush();
            m.info("Collection of films has been saved");

        } catch (IOException e) {
            m.warn("Saving of the file failed");
        }
    }
}
