package misc;

import java.util.regex.Pattern;

public abstract class Patterns {
    public static final Pattern sentenceLimiter = Pattern.compile("[!?.][\\s]+(?![0-9])");
    public static final Pattern wordLimiter = Pattern.compile("[^А-Яа-я]+");
    public static final Pattern space = Pattern.compile("\\s+");
    public static final Pattern syllable = Pattern.compile("[УЕЫАОЭЯИЮЁуеыаоэяиюё]{1}");
    public static final Pattern letter = Pattern.compile("[А-Яа-я]{1}");
    public static final Pattern consonant = Pattern.compile("[^УЕЫАОЭЯИЮЁуеыаоэяиюё]{1}");
    public static final Pattern tag = Pattern.compile("\\(Рис. .+\\)");
    public static final Pattern htmlTag = Pattern.compile("<(?:\"[^\"]*\"['\"]*|'[^']*'['\"]*|[^'\">])+>");
}
