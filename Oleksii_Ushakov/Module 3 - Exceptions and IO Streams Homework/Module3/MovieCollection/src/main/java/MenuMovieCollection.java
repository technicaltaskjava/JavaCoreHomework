import moviecollection.Actor;
import moviecollection.Movie;
import moviecollection.MovieCollection;
import moviecollection.exceptions.MovieCollectionLoadException;
import moviecollection.exceptions.MovieCollectionSaveException;
import consoledialog.ConsoleDialog;

import java.io.*;
import java.util.Arrays;

/**
 * @author Alexey Ushakov
 */
public class MenuMovieCollection {
    private static MovieCollection collection;

    static {
        ConsoleDialog.addMenuItems(new String[]{ "Load collection",
                "Save collection", "View collection", "Add movie",
                "Add actor", "Delete movie", "Delete actor",
                "View movies by actor" });
        ConsoleDialog.setInput(System.in);
        ConsoleDialog.setOutput(System.out);
        collection = new MovieCollection();
    }

    private static MovieCollection loadCollection(String name)
            throws MovieCollectionLoadException {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(name))) {
            MovieCollection temp = (MovieCollection) input.readObject();
            input.close();
            return temp;
        } catch (IOException | ClassNotFoundException e) {
            throw new MovieCollectionLoadException("Can`t load collection " + name, e);
        }
    }

    private static void saveCollection(MovieCollection collection, String name)
            throws MovieCollectionSaveException {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(name))) {
            output.writeObject(collection);
            output.close();
        } catch (IOException e) {
            throw new MovieCollectionSaveException("Can`t save collection " + name, e);
        }
    }

    private static void printMovieCollection(MovieCollection collection) {
        if (!collection.isEmpty()) {
            int index = 1;
            for (Movie movie : collection.getMovies()) {
                System.out.println("Movie [" + (index++) + "]: \"" + movie.getName() + "\"");
                System.out.println("Actors:\n" + Arrays.toString(movie.getActors()));
                System.out.println("Description: \"" + movie.getDescription() + "\"\n");
            }
        } else {
            ConsoleDialog.println("Collection is empty");
        }
    }

    private static void dialogLoadCollection() {
        ConsoleDialog.println("Input a file to load ");
        try {
            String fileName = ConsoleDialog.getUserAnswerString();
            collection = loadCollection(fileName);
            ConsoleDialog.println("Collection load success");
        } catch (IOException e) {
            ConsoleDialog.println(e.getMessage());
        }
    }

    private static void dialogSaveCollection() {
        ConsoleDialog.println("Input a file to save ");
        try {
            String fileName = ConsoleDialog.getUserAnswerString();
            saveCollection(collection, fileName);
            ConsoleDialog.println("Collection save success");
        } catch (IOException e) {
            ConsoleDialog.println(e.getMessage());
        }
    }

    private static void dialogViewCollection() {
        printMovieCollection(collection);
    }

    private static void dialogAddMovie() {
        try {
            String movieName = ConsoleDialog.getUserAnswerString("Input movie name");
            String movieDescription = ConsoleDialog.getUserAnswerString("Input movie description");
            Movie newMovie = new Movie(movieName, movieDescription);
            collection.addMovie(newMovie);

            do {
                dialogAddActor(collection.getMoviesCount());
            } while (ConsoleDialog.askUser("Add more actors"));

        } catch (IOException e) {
            ConsoleDialog.println(e.getMessage());
        }
    }

    private static void dialogAddActor() {
        try {
            int movieIndex = ConsoleDialog.getUserAnswerInt("Input movie number");
            dialogAddActor(movieIndex);
        } catch (IOException e) {
            ConsoleDialog.println(e.getMessage());
        }
    }

    private static void dialogAddActor(int movieNumber) {
        movieNumber--;
        try {
            if (collection.isCorrectMovieIndex(movieNumber)) {
                String actorName = ConsoleDialog.getUserAnswerString("Input actor name");
                String actorSurname = ConsoleDialog.getUserAnswerString("Input actor surname");
                Actor actor = new Actor(actorName, actorSurname);
                collection.getMovies()[movieNumber].addActor(actor);
            } else {
                ConsoleDialog.println("Number " + (movieNumber + 1) + " of the movie does not exist");
            }
        } catch (IOException e) {
            ConsoleDialog.println(e.getMessage());
        }
    }

    private static void dialogDeleteMovie() {
        try {
            int movieNumber = ConsoleDialog.getUserAnswerInt("Input movie number to delete");
            if (collection.isCorrectMovieIndex(movieNumber - 1)) {
                String movieName = collection.getMovies()[movieNumber - 1].getName();
                collection.deleteMovie(movieNumber - 1);
                ConsoleDialog.println("Movie " + movieName + " deleted");
            } else {
                ConsoleDialog.println("Number " + movieNumber + " of the movie does not exist");
            }
        } catch (IOException e) {
            ConsoleDialog.println(e.getMessage());
        }
    }

    private static void dialogDeleteActor() {
        try {
            String actorName = ConsoleDialog.getUserAnswerString("Input actor name");
            String actorSurname = ConsoleDialog.getUserAnswerString("Input actor surname");
            Actor actor = new Actor(actorName, actorSurname);
            int movieNumber = ConsoleDialog.getUserAnswerInt("Input the number of the movie " +
                                                                     "from witch you want to remove the actor");

            if (collection.getMovies()[movieNumber - 1].isActorInMovie(actor)) {
                collection.getMovies()[movieNumber - 1].deleteActor(actor);
                ConsoleDialog.println("Actor " + actorName + " " + actorSurname + " deleted from " +
                                              collection.getMovies()[movieNumber - 1].getName());
            } else {
                ConsoleDialog.println(actorName + " " + actorSurname + " did not act in this movie");
            }
        } catch (IOException e) {
            ConsoleDialog.println(e.getMessage());
        }
    }

    private static void dialogViewActorFilms() {
        try {
            String actorName = ConsoleDialog.getUserAnswerString("Input actor name");
            String actorSurname = ConsoleDialog.getUserAnswerString("Input actor surname");
            Actor actor = new Actor(actorName, actorSurname);

            Movie[] movies = collection.getMoviesByActor(actor);

            if (movies.length != 0) {
                ConsoleDialog.println(actor.toString());
                ConsoleDialog.println(Arrays.toString(movies));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            collection = loadCollection("MovieCollection.bin");
        } catch (MovieCollectionLoadException e) {
            ConsoleDialog.println("Can`t load default movie collection. It will be fill random");
            collection.fillRandom();
        }

        int userChoice = -1;

        while (userChoice != ConsoleDialog.EXIT_CODE) {
            try {
                ConsoleDialog.printMenu();
                userChoice = ConsoleDialog.getUserAnswerInt();

                switch (userChoice) {
                    case ConsoleDialog.EXIT_CODE:
                        saveCollection(collection, "MovieCollection.bin");
                        ConsoleDialog.close();
                        break;
                    case 1:
                        dialogLoadCollection();
                        break;
                    case 2:
                        dialogSaveCollection();
                        break;
                    case 3:
                        dialogViewCollection();
                        break;
                    case 4:
                        dialogAddMovie();
                        break;
                    case 5:
                        dialogAddActor();
                        break;
                    case 6:
                        dialogDeleteMovie();
                        break;
                    case 7:
                        dialogDeleteActor();
                        break;
                    case 8:
                        dialogViewActorFilms();
                        break;
                    default:
                        ConsoleDialog.print("Unknown menu item " + userChoice);
                        break;
                }

                ConsoleDialog.println();
            } catch (Exception e) {
                ConsoleDialog.close();
                break;
            }
        }
    }
}
