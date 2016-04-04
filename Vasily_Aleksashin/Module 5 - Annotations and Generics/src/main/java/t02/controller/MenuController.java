package t02.controller;


import t01.exception.ExitException;
import t02.model.CompareUtils;
import t02.model.PersonService;
import t02.model.TrackComparator;
import t02.model.TrackService;
import t02.model.entity.Person;
import t02.model.entity.Track;
import t02.model.exception.FileIOException;
import t02.model.exception.ParameterIsNullException;

import java.util.Arrays;

class MenuController {

	void show(final MainController mainController) throws ExitException {
		while (true) {
			mainController.print("============================================");
			mainController.print("\tMAIN MENU FOR TASK 2");
			mainController.print("============================================");
			mainController.print("[0] Show result with Comparable\n" +
					"[1] Show result with Comparator\n" +
					"[2] Exit");
			mainController.print("============================================");
			mainController.print("Enter menu item:");
			String input = mainController.read();
			switch (input) {
				case "0":
					personsResult(mainController);
					break;
				case "1":
					tracksResult(mainController);
					break;
				case "2":
					throw new ExitException();
				default:
					mainController.print(String.format("Entered menu item '%s' incorrect, expected 0 - 2", input));
			}
		}

	}
	
	private void personsResult(final MainController mainController) {
		PersonService service = new PersonService();
		try {
			Person[] persons = service.getPersons("src/main/resources/person_library.txt");
			Person min = CompareUtils.min(persons);
			Person max = CompareUtils.max(persons);
			Person median = CompareUtils.median(persons);
			StringBuilder builder = new StringBuilder("Result with Comparable:");
			builder.append("\n");
			builder.append(Arrays.toString(persons)).append("\n");
			builder.append("Min element: ").append(min).append("\n");
			builder.append("Max element: ").append(max).append("\n");
			builder.append("Median element: ").append(median);
			mainController.print(builder.toString());
		} catch (FileIOException | ParameterIsNullException e) {
			mainController.print(e.getMessage());
		}
	}

	private void tracksResult(final MainController mainController) {
		TrackService service = new TrackService();
		try {
			TrackComparator comparator = new TrackComparator();
			Track[] tracks = service.getTracks("src/main/resources/track_library.txt");
			Track min = CompareUtils.min(tracks, comparator);
			Track max = CompareUtils.max(tracks, comparator);
			Track median = CompareUtils.median(tracks, comparator);
			StringBuilder builder = new StringBuilder("Result with Comparable:");
			builder.append("\n");
			builder.append(Arrays.toString(tracks)).append("\n");
			builder.append("Min element: ").append(min).append("\n");
			builder.append("Max element: ").append(max).append("\n");
			builder.append("Median element: ").append(median).append("\n");
			mainController.print(builder.toString());
		} catch (FileIOException | ParameterIsNullException e) {
			mainController.print(e.getMessage());
		}
	}
}
