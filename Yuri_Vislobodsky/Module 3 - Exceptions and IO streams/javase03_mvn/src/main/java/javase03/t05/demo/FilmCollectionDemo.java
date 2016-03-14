package javase03.t05.demo;

import java.util.Scanner;
import javase03.common.exception.MyFileNotFoundException;
import javase03.common.exception.MyClassNotFoundException;
import javase03.common.exception.MyIOException;
import javase03.common.lib.Lib;
import javase03.t05.objects.*;
import javase03.t05.serialization.*;

/**
 * Working with film collection (demo class) 
 * @author Yury.Vislobodsky
 *
 */
public class FilmCollectionDemo {
	private FilmCollection myFilmCollection;
	private Scanner in;
	private final static String CONFIG_FILE_NAME = "config.dat";
	
	public FilmCollectionDemo() {
		myFilmCollection = new FilmCollection();
		in = new Scanner(System.in);
	}
			
	public void doAddFilm() {
		System.out.print("Input new film title : ");
		myFilmCollection.addFilm(new Films(in.next()));
	}

	public void doMenuFilms() {
		System.out.println("Films collection:");
		for (int index = 0; index < myFilmCollection.getFilmsCount(); index++) {
			System.out.println(index+" - " + 
								myFilmCollection.getFilmByIndex(index).getName());
		}
	}		
		
	public void doDeleteFilm() {
		doMenuFilms();
		System.out.print("Input # : ");
		Films film = myFilmCollection.getFilmByIndex(Lib.inputInteger(in));
		if (film != null) { 
			myFilmCollection.deleteFilm(film);
		}
	}	
	
	public void doMenuFilmActors() {
		doMenuFilms();
		System.out.print("Input # : ");
		Films film = myFilmCollection.getFilmByIndex(Lib.inputInteger(in));
		if (film != null) { 
			System.out.println("Actors of film " + film.getName() + " :");
			for (int index = 0; index < film.getActorsCount(); index++) {
				System.out.println(index+" - " + 
									film.getActorByIndex(index).getName());
			}
		}	
	}		
	
	public void doDeleteActorFromFilm() {
		doMenuFilms();
		System.out.print("Input # : ");
		Films film = myFilmCollection.getFilmByIndex(Lib.inputInteger(in));
		if (film != null) { 
			System.out.println("Actors of film " + film.getName() + " :");
			for (int index = 0; index < film.getActorsCount(); index++) {
				System.out.println(index+" - " + 
									film.getActorByIndex(index).getName());
			}	
			if (film.getActorsCount() > 0) {
				System.out.print("Input # : ");
				Actors actor = film.getActorByIndex(Lib.inputInteger(in));
				if (actor != null) {
					film.deleteActor(actor);
				}
			}
		}	
	}		
	
	public void doAddActorToFilm() {
		doMenuFilms();
		System.out.print("Input # : ");
		int indexFilm = Lib.inputInteger(in);
		Films film = myFilmCollection.getFilmByIndex(indexFilm);
		if (film != null) {
			Actors[] actors = myFilmCollection.getAllActors();
			if (actors != null) {
				System.out.println("All actors :");
				for (int index = 0; index < actors.length; index++) {
					System.out.println(index + " - " + actors[index].getName());
				}
			}
			int indexAdd = (actors==null) ? 0 : actors.length;
			System.out.println(indexAdd + " - Add new actor");
			System.out.print("Input # : ");
			int indexActor = Lib.inputInteger(in);
			if (indexActor == indexAdd) {
				System.out.print("Input new actor name : ");
				myFilmCollection.addActor(indexFilm, new Actors(in.next()));
			} else if (actors != null) {
				myFilmCollection.addActor(indexFilm, actors[indexActor]);
			}	
		}			
	}
	
	public int selectMainMenu() {
		System.out.println();
		System.out.println("MAIN MENU : ");
		System.out.println("1 - Add a new film ");
		System.out.println("2 - Delete a film ");
		System.out.println("3 - Show films list");
		System.out.println("4 - Add an actor into film");
		System.out.println("5 - Delete an actor from film");
		System.out.println("6 - Show actors list of film");
		System.out.println("0 - Quit");
		System.out.print("Your choice : ");
		return Lib.inputInteger(in);
	}

	public int selectDoContinue(int selectedItem) {
		if (selectedItem != 0) {
			selectedItem = Lib.selectContinue(in);
		}
		return selectedItem;
	}
	
	public void doSerialize() {
		try {
			FilmCollectionSerialization.serializeFilmCollection(CONFIG_FILE_NAME, 
																	myFilmCollection);
		} catch (MyIOException e) {
			System.out.println(e);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void doDeserialize() {
		try {
			myFilmCollection = 
					FilmCollectionSerialization.deserializeFilmCollection(CONFIG_FILE_NAME);
		} catch (MyFileNotFoundException e) {
			// System.out.println(e);
		} catch (MyClassNotFoundException e) {
			System.out.println(e);			
		} catch (MyIOException e) {
			System.out.println(e);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		FilmCollectionDemo filmCollectionDemo = new FilmCollectionDemo();
		int selectedItem;
		
		// restore from file
		filmCollectionDemo.doDeserialize();
		
		do {
			selectedItem = filmCollectionDemo.selectMainMenu();
			
			switch (selectedItem) {
			case 1: filmCollectionDemo.doAddFilm();
					break;
					
			case 2: filmCollectionDemo.doDeleteFilm();
					break;
					
			case 3: filmCollectionDemo.doMenuFilms();
					break;
					
			case 4: filmCollectionDemo.doAddActorToFilm();
					break;
					
			case 5: filmCollectionDemo.doDeleteActorFromFilm();
					break;
					
			case 6: filmCollectionDemo.doMenuFilmActors();
					break;
			}			
			selectedItem = filmCollectionDemo.selectDoContinue(selectedItem);			
		} while (selectedItem != 0);
		
		// save to file
		filmCollectionDemo.doSerialize();	
	}

}
