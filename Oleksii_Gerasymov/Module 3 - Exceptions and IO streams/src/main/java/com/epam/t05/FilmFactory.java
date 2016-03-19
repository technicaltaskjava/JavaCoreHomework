package main.java.com.epam.t05;

import main.java.com.epam.StreamsAndExceptions;

import java.io.IOException;

public class FilmFactory {

    public static void mainMenu() {

        FilmsCollection myFilms = new FilmsCollection();

        boolean returnToMainMenu = false;

        while (returnToMainMenu == false) {
                System.out.println("Choose the action:");
                System.out.println("[1] Show full collection.");
                System.out.println("[2] Restore collection.");
                System.out.println("[3] Add film.");
                System.out.println("[4] Remove film.");
                System.out.println("[5] Save collection.");
                System.out.println("[6] Return to main menu.");

                String userLine = StreamsAndExceptions.userInput.nextLine();
            try {
                int numberOfTask = Integer.parseInt(userLine);
                switch (numberOfTask) {

                    case 1: {
                        if (myFilms.getActualCountOfFilms() != 0) {
                            System.out.println("Total amount of films is : " + String.valueOf(myFilms.getActualCountOfFilms()));
                            for (Film currentFilm : myFilms.getFilms()) {
                                System.out.println("Film : " + currentFilm.getFilmName());
                                System.out.println("Actors : ");
                                for (String actor : currentFilm.getActors()) {
                                    System.out.println(actor);
                                }
                            }
                        }
                        else {
                            System.out.println("Collection is empty.");
                        }
                        break;
                    }

                    case 2: {
                        myFilms = FilmsCollection.restoreFilms();
                        System.out.println("Collection restored.");
                        break;
                    }

                    case 3: {
                        System.out.println("Enter the name of film.");
                        String filmName = StreamsAndExceptions.userInput.nextLine();
                        System.out.println("Enter the number of main actors.");
                        Integer actorsNumber = Integer.valueOf(StreamsAndExceptions.userInput.nextLine());
                        String[] actorsList = new String[actorsNumber];
                        for (int actorIndex = 0; actorIndex < actorsNumber; actorIndex++) {
                            System.out.println("Enter the name of " + String.valueOf(actorIndex+1) + " actor.");
                            actorsList[actorIndex] = StreamsAndExceptions.userInput.nextLine();
                        }
                        myFilms.addFilm(filmName, actorsList);
                        System.out.println("New film added to collection.");
                        break;
                    }

                    case 4: {
                        System.out.println("Enter the name of film.");
                        String filmName = StreamsAndExceptions.userInput.nextLine();
                        myFilms.deleteFilm(filmName);
                        System.out.println("Film '" + filmName + "' removed from collection.");
                        break;
                    }

                    case 5: {
                        FilmsCollection.saveFilms(myFilms);
                        System.out.println("Collection saved.");
                        break;
                    }

                    case 6: {
                        returnToMainMenu = true;
                        break;
                    }

                    default: {
                        System.out.println("Incorrect number. You must input one digit from 1 to 6.\n");
                        break;
                    }
                }
            }
            catch (NumberFormatException userException) {
                System.out.println("Incorrect input. You must input digit from 1 to 6.\n");
            }
            catch (IOException e) {
                System.out.println("IO Exception.\n");
            }
            catch (ClassNotFoundException e) {
                System.out.println("Incorrect class.\n");
            }
        }
    }

}
