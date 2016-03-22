package model.t01;

import exeption.ParameterIsNullException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TextProcessorTest {
	@Test
	public void testNormalize() throws ParameterIsNullException {
		String target = "\tHello\n     Java.\t\t\tTarget Text";
		String expected = " Hello\n Java. Target Text";
		String actual = TextProcessor.normalizeTab(target);
		assertEquals(expected, actual);
	}

	@Test
	public void testNormalizeCustom() throws ParameterIsNullException {
		String regex = ",";
		String replacement = ".";
		String target = "Hello Java, Target Text,";
		String expected = "Hello Java. Target Text.";
		String actual = TextProcessor.normalizeTab(target, regex, replacement);
		assertEquals(expected, actual);
	}

	@Test
	public void testRemoveWord() throws ParameterIsNullException {
		String target = "Hello Java,Как Какf Target Какf\tText,";
		String expected = "Hello ,Как  Target \t,";
		String actual = TextProcessor.removeWord(target, 4);
		assertEquals(expected, actual);
	}

	@Test
	public void testRemoveSameLatterInWord() throws ParameterIsNullException {
		String target = "Как Конструкторы, использующие Рис. 5.1. Вывод кириллической.";
		String expected = "Ка Конструторы, использующе Рис. 5.1. Выод кирилличесой.";
		assertEquals(expected, TextProcessor.removeDuplicateLatter(target));
	}

	@Test
	public void testRemoveSameLatterInOneWord() throws ParameterIsNullException {
		String target = "Конструкторы";
		String expected = "Конструторы";
		assertEquals(expected, TextProcessor.removeDuplicateLatter(target));
	}

	@Test
	public void testSortByVowelsTwoWords() throws ParameterIsNullException {
		String target = "использующие Конструкторы";
		String expected = "Конструкторы\nиспользующие\n";
		assertEquals(expected, TextProcessor.sortByVowels(target));
	}

	@Test
	public void testSortByVowelsThreeWords() throws ParameterIsNullException {
		String target = "использующие выводятся Конструкторы. ";
		String expected = "Конструкторы\nвыводятся\nиспользующие\n";
		assertEquals(expected, TextProcessor.sortByVowels(target));
	}

	@Test
	public void testSortByVowelsFourWords() throws ParameterIsNullException {
		String target = "использующие Конструкторы. таблиц выводятся ";
		String expected = "Конструкторы\nтаблиц\nвыводятся\nиспользующие\n";
		assertEquals(expected, TextProcessor.sortByVowels(target));
	}

	@Test
	public void testSortByVowelsFourWordsWithName() throws ParameterIsNullException {
		String target = "использующие таблиц Конструкторы. выводятся ";
		String expected = "Конструкторы\nтаблиц\nвыводятся\nиспользующие\n";
		assertEquals(expected, TextProcessor.sortByVowels(target, true));
	}

	@Test(expected = ParameterIsNullException.class)
	public void testSortByVowelsWithNull() throws ParameterIsNullException {
		TextProcessor.sortByVowels(null);

	}

	@Test(expected = ParameterIsNullException.class)
	public void testSortByVowelsWithEmpty() throws ParameterIsNullException {
		TextProcessor.sortByVowels("");
	}

	@Test
	public void testWordsReplacement() throws ParameterIsNullException {
		String target = "Как создать строку\n" +
				"Самый простой способ создать строку — это организовать ссылку типа string на строку-константу.\n" +
				"Если константа длинная, можно записать ее в нескольких строках текстового редактора, связывая их " +
				"операцией сцепления.\n" +
				"Замечание";
		String expected = "строку создать Как\n" +
				"строку-константу простой способ создать строку — это организовать ссылку типа string на Самый.\n" +
				"сцепления константа длинная, можно записать ее в нескольких строках текстового редактора, связывая их " +
				"операцией Если.\n" +
				"Замечание";
		assertEquals(expected, TextProcessor.wordsReplacement(target));

	}

	@Test
	public void testWordsReplacementDiff() throws ParameterIsNullException {
		String target = "Не забывайте разницу между пустой строкой string s = \"\" , не содержащей ни одного " +
				"символа, и пустой ссылкой string s = null, не указывающей ни на какую строку и не являющейся объектом.\n" +
				"Самый правильный способ создать объект с точки зрения ООП — это вызвать его конструктор в " +
				"операции new. Класс string предоставляет вам девять конструкторов.\n" +
				"При неправильном заданий индексов offset , count или кодировки encoding возникает исключительная " +
				"ситуация.\n" +
				"Конструкторы, использующие массив байтов byteArray , предназначены для создания Unicode-строки " +
				"из массива байтовых ASCII-кодировок символов. Такая ситуация возникает при чтении ASCII-файлов, " +
				"извлечении информации из базы данных или при передаче информации по сети.\n" +
				"В самом простом случае компилятор для получения двухбайтовых символов Unicode добавит к каждому " +
				"байту старший нулевой байт. Получится диапазон ' \\u0000 ' — ' \\u00ff ' кодировки Unicode, " +
				"соответствующий кодам Latin 1. Тексты на кириллице будут выведены неправильно.\n" +
				"Если же на компьютере сделаны местные установки, как говорят на жаргоне \"установлена локаль\" " +
				"(locale) (в MS Windows это выполняется утилитой Regional Options в окне Control Panel ), то " +
				"компилятор, прочитав эти установки, создаст символы Unicode, соответствующие местной кодовой " +
				"странице. В русифицированном варианте MS Windows это обычно кодовая страница СР1251.\n" +
				"Если исходный массив с кириллическим ASCII-текстом был в кодировке СР1251, то строка Java будет " +
				"создана правильно. Кириллица попадет в свой диапазон '\\u0400'—'\\u04FF' кодировки Unicode.";
		String expected = "объектом забывайте разницу между пустой строкой string s = \"\" , не содержащей ни " +
				"одного символа, и пустой ссылкой string s = null, не указывающей ни на какую строку и не являющейся Не.\n" +
				"new правильный способ создать объект с точки зрения ООП — это вызвать его конструктор в " +
				"операции Самый. конструкторов string предоставляет вам девять Класс.\n" +
				"ситуация неправильном заданий индексов offset , count или кодировки encoding возникает исключительная При.\n" +
				"символов, использующие массив байтов byteArray , предназначены для создания Unicode-строки из " +
				"массива байтовых ASCII-кодировок Конструкторы. сети ситуация возникает при чтении ASCII-файлов, " +
				"извлечении информации из базы данных или при передаче информации по Такая.\n" +
				"байт самом простом случае компилятор для получения двухбайтовых символов Unicode добавит к " +
				"каждому байту старший нулевой В. 1 диапазон ' \\u0000 ' — ' \\u00ff ' кодировки Unicode, " +
				"соответствующий кодам Latin Получится. неправильно на кириллице будут выведены Тексты.\n" +
				"странице же на компьютере сделаны местные установки, как говорят на жаргоне \"установлена " +
				"локаль\" (locale) (в MS Windows это выполняется утилитой Regional Options в окне Control Panel " +
				"), то компилятор, прочитав эти установки, создаст символы Unicode, соответствующие местной " +
				"кодовой Если. СР1251 русифицированном варианте MS Windows это обычно кодовая страница В.\n" +
				"правильно исходный массив с кириллическим ASCII-текстом был в кодировке СР1251, то строка " +
				"Java будет создана Если. Unicode попадет в свой диапазон '\\u0400'—'\\u04FF' кодировки Кириллица.";
		assertEquals(expected, TextProcessor.wordsReplacement(target));

	}

	@Test
	public void testWordsReplacementDiffSimple() throws ParameterIsNullException {
		String target = "Если исходный массив с кириллическим ASCII-текстом был в кодировке СР1251, то строка Java будет " +
				"создана правильно. Кириллица попадет в свой диапазон '\\u0400'—'\\u04FF' кодировки Unicode.";
		String expected = "правильно исходный массив с кириллическим ASCII-текстом был в кодировке СР1251, то строка " +
				"Java будет создана Если. Unicode попадет в свой диапазон '\\u0400'—'\\u04FF' кодировки Кириллица.";
		assertEquals(expected, TextProcessor.wordsReplacement(target));

	}
}