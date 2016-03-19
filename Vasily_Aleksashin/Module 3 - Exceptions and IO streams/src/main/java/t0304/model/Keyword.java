package t0304.model;

import t01.exception.ModelException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Keyword {

	private String[] keywordArray;
	private int[] keywordCount;
	private char[] separators = new char[]{' ', ';', '(', ')', '[', ']', '{', '}', '.', ':', '/', '*'};

	public Keyword(final String fileName) throws ModelException {
		validate(fileName);
		loadKeywords(fileName);
	}

	public void findAllKeyword(final String javaCode) throws ModelException {
		validateKeywordArray();
		for (int index = 0; index < keywordArray.length; index++) {
			findKeyword(javaCode, index);
		}
	}

	public void findKeyword(final String javaCode, final int indexKeywords) throws ModelException {
		validateKeywordArray();
		validate(javaCode);
		eraseKeywordCount(indexKeywords);
		for (int indexSearch = -1; (indexSearch = javaCode.indexOf(keywordArray[indexKeywords], indexSearch + 1)) != -1; ) {
			boolean flagBefore = false;
			boolean flagAfter = false;
			for (char separator : separators) {
				if (indexSearch == 0) {
					flagBefore = true;
				} else {
					if (indexSearch > 0 && javaCode.charAt(indexSearch - 1) == separator) {
						flagBefore = true;
					}
				}
				if (indexSearch + keywordArray[indexKeywords].length() == javaCode.length()) {
					flagAfter = true;
				} else {
					if (indexSearch + keywordArray[indexKeywords].length() < javaCode.length() &&
							javaCode.charAt(indexSearch + keywordArray[indexKeywords].length()) == separator) {
						flagAfter = true;
					}
				}
			}
			if (flagBefore && flagAfter) {
				keywordCount[indexKeywords] += 1;
			}
		}
	}

	public String[] getKeywordArray() throws ModelException {
		validateKeywordArray();
		return Arrays.copyOf(keywordArray, keywordArray.length);
	}

	public int[] getKeywordCount() throws ModelException {
		validateKeywordArray();
		return Arrays.copyOf(keywordCount, keywordCount.length);
	}

	public String getFoundKeywords() throws ModelException {
		validateKeywordArray();
		StringBuilder builder = new StringBuilder();
		for (int index = 0; index < keywordArray.length; index++) {
			if (keywordCount[index] != 0) {
				builder.append(keywordArray[index]).append(" = ").append(keywordCount[index]).append("\n");
			}
		}
		return builder.toString();
	}

	public void loadKeywords(final String filePath) throws ModelException {
		validate(filePath);
		try (Scanner scanner = new Scanner(new File(filePath))) {
			keywordArray = null;
			int index = 0;
			while (scanner.hasNextLine()) {
				if (keywordArray == null) {
					keywordArray = new String[1];
				} else {
					keywordArray = Arrays.copyOf(keywordArray, keywordArray.length + 1);
				}
				keywordArray[index] = scanner.nextLine();
				index++;
			}
			keywordCount = new int[keywordArray.length];
		} catch (FileNotFoundException e) {
			throw new ModelException(e.getMessage());
		}
	}

	@Override
	public String toString() {
		try {
			validateKeywordArray();
		} catch (ModelException e) {
			return e.getMessage();
		}
		StringBuilder builder = new StringBuilder();
		builder.append("Keyword{");
		for (int index = 0; index < keywordArray.length; index++) {
			builder.append("\n").append(keywordArray[index]).append(" = ").append(keywordCount[index]);
		}
		builder.append("\n").append("}");
		return builder.toString();
	}

	private void validateKeywordArray() throws ModelException {
		if (keywordArray == null) {
			throw new ModelException("Keywords array is NULL. Load data first.");
		}
	}

	private void validate(final String javaCode) throws ModelException {
		if (javaCode == null) {
			throw new ModelException("Parameter can not be NULL");
		}
	}

	private void eraseKeywordCount(final int indexKeywords) throws ModelException {
		if (indexKeywords > keywordArray.length) {
			throw new ModelException("Index of keyword can not be more than count of keywords");
		}
		keywordCount[indexKeywords] = 0;
	}
}
