package t0304.model;

import t01.exception.ModelException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Keyword {

	private String[] keywordArray = new String[50];
	private int[] keywordCount = new int[keywordArray.length];
	private char[] separators = new char[]{' ', ';', '(', ')', '[', ']', '{', '}', '.', ':', '/', '\\', '*'};

	public Keyword(final String fileName) throws FileNotFoundException, ModelException {
		validate(fileName);
		init(fileName);
	}

	public void findAllKeyword(final String javaCode) throws ModelException {
		for (int index = 0; index < keywordArray.length; index++) {
			findKeyword(javaCode, index);
		}
	}

	public void findKeyword(final String javaCode, final int indexKeywords) throws ModelException {
		validate(javaCode);
		eraseKeywordCount(indexKeywords);
		for (int indexSearch = -1; (indexSearch = javaCode.indexOf(keywordArray[indexKeywords], indexSearch + 1)) != -1; ) {
			boolean flagBefore = false;
			boolean flagAfter = false;
			for (int indexSeparator = 0; indexSeparator < separators.length; indexSeparator++) {
				if (indexSearch == 0) {
					flagBefore = true;
				} else {
					if (indexSearch > 0 && javaCode.charAt(indexSearch - 1) == separators[indexSeparator]) {
						flagBefore = true;
					}
				}
				if (indexSearch + keywordArray[indexKeywords].length() == javaCode.length()) {
					flagAfter = true;
				} else {
					if (indexSearch + keywordArray[indexKeywords].length() < javaCode.length() &&
							javaCode.charAt(indexSearch + keywordArray[indexKeywords].length()) == separators[indexSeparator]) {
						flagAfter = true;
					}
				}
			}
			if (flagBefore && flagAfter) {
				keywordCount[indexKeywords] += 1;
			}
		}
	}

	public String[] getKeywordArray() {
		return Arrays.copyOf(keywordArray, keywordArray.length);
	}

	public int[] getKeywordCount() {
		return Arrays.copyOf(keywordCount, keywordCount.length);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Keyword{");
		for (int index = 0; index < keywordArray.length; index++) {
			builder.append("\n").append(keywordArray[index]).append(" = ").append(keywordCount[index]);
		}
		builder.append("\n").append("}");
		return builder.toString();
	}

	private void init(final String filePath) throws FileNotFoundException, ModelException {
		validate(filePath);
		try (Scanner scanner = new Scanner(new File(filePath))) {
			int index = 0;
			while (scanner.hasNextLine()) {
				keywordArray[index] = scanner.nextLine();
				index++;
			}
		} catch (FileNotFoundException e) {
			throw e;
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
