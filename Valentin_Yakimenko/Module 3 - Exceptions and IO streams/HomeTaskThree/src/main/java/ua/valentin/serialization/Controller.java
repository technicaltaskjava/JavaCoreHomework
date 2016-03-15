package ua.valentin.serialization;

import ua.valentin.serialization.model.Actor;
import ua.valentin.serialization.model.Movie;
import ua.valentin.serialization.model.MovieCollection;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by valentin.yakimenko on 15.03.16.
 */
public class Controller {
    private MovieCollection movies;
    private Set<Actor> actors;
    private Scanner scanner = new Scanner(System.in);
    public Controller() {
        this.movies = new MovieCollection();
        actors = getActors();
    }

    private Set<Actor> getActors() {
        return movies.getActors();
    }

    private void addMovie() {
        movies.addMovie(getMovie());
    }

    private void delMovies() {
        movies.removeMovie(getNumberOfMovie());
    }

    private int getNumberOfMovie() {
        showAllMovies();
        askForNumberOfMovie();
        int result;
        try {
            result = Integer.valueOf(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("wrong number");
            result = -1;
        }
        return result;
    }

    private void askForNumberOfMovie() {
        System.out.println("Input number of movie:");
    }

    private void showAllMovies() {
        System.out.println(movies);
    }

    private void showMainMenu() {
        for (MainMenu menu : MainMenu.values()) {
            System.out.println(menu);
        }
    }

    private Movie getMovie() {
        String nameOfMovie = getNameOfMovie();
        Set<Actor> actorsOfMovie = getActorsOfMovie();
        return new Movie(nameOfMovie, actorsOfMovie);
    }

    private String getNameOfMovie() {
        askForNameOfMovie();
        return scanner.nextLine();
    }

    private void askForNameOfMovie() {
        System.out.println("Input name for new Movie");
    }

    private Set<Actor> getActorsOfMovie() {
        String choice;
        Actor actor = null;
        Set<Actor> result = new HashSet<Actor>();
        while (!ActorMenu.EXIT.getNumber().equals(choice = getActorMenuChoice())) {
            if (ActorMenu.ADD_NEW.getNumber().equals(choice)) {
                actor = getNewActor();
                actors.add(actor);
            } else if (ActorMenu.FROM_LIST.getNumber().equals(choice)) {
                try {
                    System.out.println(outputSetOfActors());
                    actor = getActorFromList(Integer.valueOf(scanner.nextLine()));
                } catch (NumberFormatException e) {
                    System.out.println("wrong number");
                }
            }
            if (actor != null) result.add(actor);
        }
        return result;
    }

    private Actor getActorFromList(int index) {
        return getActorByNumber(isNumberValid(index) ? index : -1);
    }

    private boolean isNumberValid(int numberOfActor) {
        return numberOfActor >= 0 && numberOfActor < actors.size();
    }

    private Actor getActorByNumber(int index) {
        int count = 0;
        for (Actor actor : actors) {
            if (count++ == index) return actor;
        }
        return null;
    }

    private Actor getNewActor() {
        return new Actor(getNewName(), getNewSurname());
    }

    private String getNewSurname() {
        AskForSurname();
        return scanner.nextLine();
    }

    private void AskForSurname() {
        System.out.println("Input surname for new actor");
    }

    private String getNewName() {
        AskForName();
        return scanner.nextLine();
    }

    private void AskForName() {
        System.out.println("Input name for new actor");
    }

    private String getMainMenuChoice() {
        showMainMenu();
        return scanner.nextLine();
    }

    public String getActorMenuChoice() {
        showActorMenu();
        return scanner.nextLine();
    }

    private String outputSetOfActors() {
        int count = 0;
        StringBuilder result = new StringBuilder();
        for (Actor actor : actors) {
            result.append(count++).append(" : ")
                    .append(actor).append("\n");
        }
        return result.toString();
    }

    private void showActorMenu() {
        for (ActorMenu menu : ActorMenu.values()) {
            System.out.println(menu);
        }
    }

    public void mainMenu() {
        String choice;
        while(!MainMenu.EXIT.getNumber().equals(choice = getMainMenuChoice())) {
            if (MainMenu.VIEW_ALL.getNumber().equals(choice)) {
                showAllMovies();
            } else if (MainMenu.DEL.getNumber().equals(choice)) {
                delMovies();
            } else if (MainMenu.ADD_NEW.getNumber().equals(choice)) {
                addMovie();
            }
        }
        saveState();
        closeDescriptors();
    }

    private void closeDescriptors() {
        scanner.close();
    }

    private void saveState() {

    }
 }
