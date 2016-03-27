package model.t01;

import exeption.ParameterIsNullException;
import model.t02.CrazyLogger;
import util.Constant;
import util.Validator;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.PatternSyntaxException;

public class TextProcessor {
	private static CrazyLogger logger = CrazyLogger.getLogger(TextProcessor.class);

	public static String normalizeTab(final String input) throws ParameterIsNullException {
		return normalizeTab(input, null, null);
	}

	public static String normalizeTab(final String input, final String regex, final String replacement) throws ParameterIsNullException {
		logger.log(1, "Validate input string");
		Validator.validate(input);
		logger.log(1, "Select old value");
		String oldValue = regex != null ? regex : Constant.NORMALIZE;
		logger.log(1, "Select new value");
		String newValue = replacement != null ? replacement : Constant.SPACE;
		logger.log(1, "Return replaced string");
		return input.replaceAll(oldValue, newValue);
	}

	public static String removeWord(final String input, final int length) throws ParameterIsNullException {
		return removeWord(input, null, length);
	}

	public static String removeWord(final String input, final String firstLattes, final int length) throws ParameterIsNullException {
		logger.log(1, "Validate input string");
		Validator.validate(input);
		logger.log(1, "Select first latter");
		String first = firstLattes != null ? firstLattes : Constant.CONSONANT;
		logger.log(1, "Select word length");
		int len = length == 0 ? 1 : length;
		logger.log(1, "Prepare regexp");
		String regex = String.format(Constant.WORD_LENGTH, first, len - 1);
		logger.log(1, "Return result");
		return input.replaceAll(regex, "");
	}

	public static String removeDuplicateLatter(final String input) throws ParameterIsNullException {
		logger.log(1, "Validate input string");
		Validator.validate(input);
		StringBuilder builder = new StringBuilder();
		logger.log(1, "Prepare matcher");
		Matcher matcher = Constant.PATTERN_WORD_FIRST_LATTER.matcher(input);
		int lastIndex = input.length();
		int startIndex = 0;
		int endIndex;
		logger.log(1, "Search matcher in input string");
		while (matcher.find()) {
			endIndex = matcher.start(2) + 1;
			logger.log(1, "Added first latter");
			builder.append(input, startIndex, endIndex);
			logger.log(1, "Replace all latter in word");
			try {
				builder.append(matcher.group(3).replaceAll(matcher.group(2).toLowerCase(), ""));
			} catch (PatternSyntaxException e) {
				logger.log(3, e.getMessage());
			}
			startIndex = matcher.end(3);
		}
		logger.log(1, "Added last char");
		if (startIndex != lastIndex) {
			builder.append(input.charAt(lastIndex - 1));
		}
		logger.log(1, "Return result");
		return builder.toString();
	}

	public static String sortByVowels(final String input) throws ParameterIsNullException {
		return sortByVowels(input, false);
	}

	public static String sortByVowels(final String input, final boolean sortWithName) throws ParameterIsNullException {
		logger.log(1, "Validate input string");
		Validator.validate(input);
		logger.log(1, "Get words Array");
		Object[][] compareArray = getWordsArray(input);
		if (compareArray == null) {
			return Constant.NO_RESULT;
		}
		logger.log(1, "Sort words array");
		bubbleSortWordsByRate(compareArray, sortWithName);
		StringBuilder builder = new StringBuilder();
		logger.log(1, "Prepare result");
		for (Object[] word : compareArray) {
			builder.append(word[0]).append("\n");
		}
		logger.log(1, "Return result");
		return builder.toString();
	}

	public static String wordsReplacement(final String input) throws ParameterIsNullException {
		logger.log(1, "Validate input string");
		Validator.validate(input);
		StringBuilder builder = new StringBuilder();
		logger.log(1, "Get clause array");
		String[] splitClause = Constant.PATTERN_CLAUSE_DELIMITER.split(input);
		logger.log(1, "Use crooked nail");
		crookedNailForFirstLatterInClause(input, splitClause);
		int firstIndex = 0;
		int lastIndex;
		logger.log(1, "Processing clause");
		for (int index = 0; index < splitClause.length; index++) {
			lastIndex = input.indexOf(splitClause[index], firstIndex);
			logger.log(1, "Get words array");
			String[] innerSplit = Constant.PATTERN_WORD_DELIMITER.split(splitClause[index]);
			if (innerSplit.length > 1) {
				logger.log(1, "Prepare swap words");
				String firstWord = innerSplit[0];
				String lastWord = innerSplit[innerSplit.length - 1];
				String innerWords = splitClause[index].substring(firstWord.length(), splitClause[index].length() - lastWord.length());
				logger.log(1, "Use crooked nail");
				if (lastWord.matches(".*[\\.?!]")) {
					firstWord += String.valueOf(lastWord.charAt(lastWord.length() - 1));
					lastWord = lastWord.substring(0, lastWord.length() - 1);
				}
				logger.log(1, "Swap words");
				splitClause[index] = lastWord + innerWords + firstWord;
			}
			logger.log(1, "Prepare result");
			try {
				builder.append(input.substring(firstIndex, lastIndex));
			} catch (StringIndexOutOfBoundsException e) {
				logger.log(3, e.getMessage());
			}
			builder.append(splitClause[index]);
			firstIndex = lastIndex + splitClause[index].length();
		}
		logger.log(1, "Return result");
		return builder.toString();
	}

	private static void crookedNailForFirstLatterInClause(final String input, final String[] splitClause) {
		for (int index = 1; index < splitClause.length; index++) {
			int firstIndex = input.indexOf(splitClause[index]);
			int lastIndex = firstIndex + splitClause[index].length();
			if (lastIndex < input.length()) {
				lastIndex++;
			}
			splitClause[index] = input.substring(firstIndex - 1, lastIndex);
		}
	}

	private static Object[][] getWordsArray(final String input) {
		Object[][] compareArray = null;
		logger.log(1, "Prepare matcher for words");
		Matcher matcher = Constant.PATTERN_WORD.matcher(input);
		logger.log(1, "Search matcher in input string");
		while (matcher.find()) {
			double vowelsCount = 0;
			compareArray = resizeArray(compareArray);
			String word = matcher.group(1);
			logger.log(1, "Prepare inner matcher for vowels");
			Matcher innerMatcher = Constant.PATTERN_VOWELS.matcher(word);
			logger.log(1, "Search inner matcher in word");
			while (innerMatcher.find()) {
				vowelsCount++;
			}
			int index = compareArray.length - 1;
			logger.log(1, "Prepare result");
			compareArray[index][0] = word;
			compareArray[index][1] = vowelsCount != 0 ? word.length() / vowelsCount : vowelsCount;
		}
		logger.log(1, "Return result");
		return compareArray;
	}

	private static void bubbleSortWordsByRate(final Object[][] compareArray, final boolean sortWithName) {
		for (int indexPrimary = compareArray.length - 1; indexPrimary > 0; indexPrimary--) {
			for (int indexSecondary = 0; indexSecondary < compareArray.length - 1; indexSecondary++) {
				int multiplier = 1000;
				Double firstElement = (Double) compareArray[indexSecondary][1] * multiplier;
				Double secondElement = (Double) compareArray[indexSecondary + 1][1] * multiplier;
				int compareRate = firstElement.compareTo(secondElement);
				if (compareRate < 0) {
					changeElements(compareArray, indexSecondary);
				}
				if (sortWithName) {
					int compareWords = ((String) compareArray[indexSecondary][0]).compareTo((String)
							compareArray[indexSecondary + 1][0]);
					if (compareRate == 0 && compareWords > 0) {
						changeElements(compareArray, indexSecondary);
					}
				}
			}
		}
	}

	private static void changeElements(final Object[][] compareArray, final int index) {
		Object[] tempArrayForSort = compareArray[index];
		compareArray[index] = compareArray[index + 1];
		compareArray[index + 1] = tempArrayForSort;
	}

	private static Object[][] resizeArray(Object[][] compareArray) {
		if (compareArray == null) {
			compareArray = new Object[1][2];
		} else {
			compareArray = Arrays.copyOf(compareArray, compareArray.length + 1);
			compareArray[compareArray.length - 1] = new Object[2];
		}
		return compareArray;
	}
}
