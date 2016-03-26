package textformatter.textformatterutils;

/**
 * @author Alexey Ushakov
 */
public interface TextPatterns {
    String PATTERN_NEW_SENTENCE = "(?<=[.!?]) (?=[А-Я].*)";
    String PATTERN_TABS_OR_SPACES = "[\\s\\t\\xA0]+"; /* \xA0 - &nbsp; */
    String PATTERN_VOWELS = "[аеиоуыэюяАЕИОУЫЭЮЯ]";
    String PATTERN_TYPOGRAPHIC_SYMBOL = "[\\x21-\\x2F\\x3A-\\x3F]";
    String PATTERN_CONSONANTS = "[б-джзй-нп-тф-щБ-ДЖЗЙ-НП-ТФ-Щ]";
}
