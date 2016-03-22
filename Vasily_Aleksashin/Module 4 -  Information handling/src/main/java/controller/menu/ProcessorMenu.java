package controller.menu;

import exeption.ExitException;
import exeption.ParameterIsNullException;
import model.t01.TextProcessor;
import model.t02.CrazyLogger;
import model.t03.HtmlProcessor;
import util.Constant;

public class ProcessorMenu {
	private static CrazyLogger logger = CrazyLogger.getLogger(ProcessorMenu.class);

	private String normalizeText;
	private String tempText;

	void showHtml(final MainMenu menu) throws ExitException, ParameterIsNullException {
		logger.log(1, "Validate source text");
		validate(OperationMenu.sourceHtml, false);
		while (true) {
			menu.getController().print(Constant.SEPARATOR);
			menu.getController().print("\t\tPROCESSOR MENU WITH HTML");
			menu.getController().print(Constant.SEPARATOR);
			menu.getController().print("[0] Search image links in clause\n" +
					"[1] Back to Text menu");
			menu.getController().print(Constant.SEPARATOR);
			menu.getController().print(Constant.ENTER_MENU_NUMBER);
			logger.log(1, "Enter menu number");
			String inputMenuNumber = menu.getController().read();
			switch (inputMenuNumber) {
				case "0":
					search();
					showResult(menu);
					break;
				case "1":
					logger.log(1, "Return to Text menu");
					OperationMenu.targetHtml = tempText;
					throw new ExitException();
				default:
					menu.getController().print(String.format("Menu number '%s' not found, expected 0 - 2", inputMenuNumber));
			}
		}
	}

	void showText(final MainMenu menu) throws ExitException, ParameterIsNullException {
		logger.log(1, "Validate source text");
		validate(OperationMenu.sourceText, true);
		while (true) {
			menu.getController().print(Constant.SEPARATOR);
			menu.getController().print("\t\tPROCESSOR MENU WITH TEXT");
			menu.getController().print(Constant.SEPARATOR);
			menu.getController().print("[0] Swap the first and last word in the clause\n" +
					"[1] Sort words by proportion of vowels\n" +
					"[2] Delete the words specified length that start with a consonant\n" +
					"[3] Remove in words all latter like first latter\n" +
					"[4] Back to Text menu");
			menu.getController().print(Constant.SEPARATOR);
			menu.getController().print(Constant.ENTER_MENU_NUMBER);
			logger.log(1, "Enter menu number");
			String inputMenuNumber = menu.getController().read();
			switch (inputMenuNumber) {
				case "0":
					swap();
					showResult(menu);
					break;
				case "1":
					sort(menu);
					showResult(menu);
					break;
				case "2":
					removeWords(menu);
					showResult(menu);
					break;
				case "3":
					removeLetters();
					showResult(menu);
					break;
				case "4":
					logger.log(1, "Return to Text menu");
					OperationMenu.targetText = tempText;
					throw new ExitException();
				default:
					menu.getController().print(String.format("Menu number '%s' not found, expected 0 - 2", inputMenuNumber));
			}
		}
	}

	private void showResult(final MainMenu menu) {
		logger.log(1, "Print result");
		String input;
		boolean flag = true;
		while (flag) {
			menu.getController().print("Do you want show result? y/n");
			input = menu.getController().read();
			switch (input.toLowerCase()) {
				case "y":
					menu.getController().print(tempText);
					flag = false;
					break;
				case "n":

					flag = false;
					break;
				default:
					menu.getController().print(String.format("Enter '%s' but expected 'Y' or 'N'", input));
			}

		}
	}

	private void search() throws ParameterIsNullException {
		logger.log(1, "Search image links in clause");
		tempText = HtmlProcessor.searchImgLink(normalizeText);
	}

	private void removeLetters() throws ParameterIsNullException {
		logger.log(1, "Remove in words all latter like first latter");
		tempText = TextProcessor.removeDuplicateLatter(normalizeText);
	}

	private void removeWords(final MainMenu menu) throws ParameterIsNullException {
		logger.log(1, "Delete the words specified length that start with a consonant");
		String input = "";
		int length;
		while (true) {
			menu.getController().print("Enter word length for remove:");
			try {
				input = menu.getController().read();
				length = Integer.parseInt(input);
				tempText = TextProcessor.removeWord(normalizeText, length);
				break;
			} catch (NumberFormatException e) {
				logger.log(3, String.format("Enter '%s' but expected number", input));
				menu.getController().print(String.format("Enter '%s' but expected number", input));
			}
		}
	}

	private void sort(final MainMenu menu) throws ParameterIsNullException {
		logger.log(1, "Sort words by proportion of vowels");
		String input;
		boolean flag = true;
		while (flag) {
			menu.getController().print("Do you want sort words by name? y/n");
			input = menu.getController().read();
			switch (input.toLowerCase()) {
				case "y":
					tempText = TextProcessor.sortByVowels(normalizeText, true);
					flag = false;
					break;
				case "n":
					tempText = TextProcessor.sortByVowels(normalizeText);
					flag = false;
					break;
				default:
					menu.getController().print(String.format("Enter '%s' but expected 'Y' or 'N'", input));
			}

		}
	}

	private void swap() throws ParameterIsNullException {
		logger.log(1, "Swap the first and last word in the clause");
		tempText = TextProcessor.wordsReplacement(normalizeText);
	}

	private void validate(final String source, final boolean normalize) throws ParameterIsNullException {
		if (source == null) {
			logger.log(3, "Source text is NULL, input source first.");
			throw new ParameterIsNullException("Source text is NULL, input source first.");
		}
		if (normalize) {
			normalizeText = TextProcessor.normalizeTab(source);
		} else {
			normalizeText = source;
		}
	}
}
