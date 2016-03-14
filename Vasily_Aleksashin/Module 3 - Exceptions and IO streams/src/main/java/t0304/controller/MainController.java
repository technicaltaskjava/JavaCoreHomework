package t0304.controller;

import t01.exception.ExitException;
import t01.exception.ModelException;
import t01.view.View;
import t01.view.impl.ConsoleViewImpl;
import t0304.model.ByteStream;
import t0304.model.CharStream;
import t0304.model.Keyword;
import t0304.model.UserStream;

import java.io.File;

public class MainController {
	private static final String SEPARATOR = "=======================================================";
	private static final String DEFAULT_KEYWORDS_FILE = "src/main/resources/java_keywords.txt";

	private View view = new ConsoleViewImpl();
	private Keyword keyword;

	public void run(final String[] keywordsFile) throws ExitException {
		String readFile;
		if (keywordsFile != null && keywordsFile.length != 0) {
			readFile = keywordsFile[0];
		} else {
			readFile = DEFAULT_KEYWORDS_FILE;
		}
		try {
			keyword = new Keyword(readFile);
		} catch (ModelException e) {
			view.print(SEPARATOR);
			view.print(String.format("App can not load keywords from file %s", readFile));
			view.print("\t" + e.getMessage());
			view.print(SEPARATOR);
		}
		while (true) {
			view.print(SEPARATOR);
			view.print("Menu:");
			view.print(SEPARATOR);
			view.print("[1] Load file with keywords " +
					"[2] Found keywords by ByteStream " +
					"[3] Found keywords by CharStream " +
					"[4] Exit");
			view.print(SEPARATOR);
			view.print("Enter menu number:");
			String menuNumber = view.read();
			switch (menuNumber) {
				case "1":
					loadKeywords();
					break;
				case "2":
					searchKeywords(new ByteStream());
					break;
				case "3":
					searchKeywords(new CharStream());
					break;
				case "4":
					view.close();
					throw new ExitException();
				default:
					view.print("Wrong menu number, expected from 1 to 4");
			}
		}
	}

	private void loadKeywords() {
		view.print("Enter file path:");
		String input = view.read();
		try {
			keyword.loadKeywords(input);
			view.print(String.format("Operation successful\n\tTotal load %s keywords", keyword.getKeywordArray().length));
		} catch (ModelException e) {
			view.print(e.getMessage());
		}
	}

	private void searchKeywords(final UserStream stream) {
		view.print("Enter java file path:");
		String filePath = view.read();
		String readJavaCode;
		try {
			readJavaCode = stream.read(filePath);
			keyword.findAllKeyword(readJavaCode);
			String foundKeywords = keyword.getFoundKeywords();
			view.print(String.format("Search is successful\n\tKeywords:\n%s", foundKeywords));
			String outputFile = new File(filePath).getParent() + File.separator + "result.txt";
			stream.write(outputFile, foundKeywords);
			view.print(String.format("Result of search successful write to file:\n\t'%s'", new File(outputFile).getAbsolutePath()));
		} catch (ModelException e) {
			view.print(e.getMessage());
		}
	}
}
