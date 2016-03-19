package main.java.com.epam.t04;

public class JavaKeywordsFinder {
    public static final String[] keywordsArray = {"abstract", "assert", "boolean", "break", "byte", "case", "catch",
                                                 "char", "class", "const", "continue", "default", "do", "double",
                                                 "else", "enum", "extends", "final", "finally", "float", "for",
                                                 "goto", "if", "implements", "import", "instanceof", "int",
                                                 "interface", "long", "native", "new", "package", "private",
                                                 "protected", "public", "return", "short", "static", "strictfp",
                                                 "super", "switch", "synchronized", "this", "throw", "throws",
                                                 "transient", "try", "void", "volatile", "while"
                                                 };

    public static Integer[] countKeywordEntrance(String newData) {
        Integer[] entranceArray = new Integer[keywordsArray.length];

        for (int indexArray = 0; indexArray < keywordsArray.length; indexArray++) {
            String currentWord = keywordsArray[indexArray];
            entranceArray[indexArray] = 0;
            int currentPosition = 0;
            while (newData.indexOf(keywordsArray[indexArray], currentPosition) != -1) {
                    int currentPoint = newData.indexOf(keywordsArray[indexArray], currentPosition);

                    if ((!Character.isLetterOrDigit(newData.charAt(currentPoint - 1))) &&
                            (!Character.isLetterOrDigit(newData.charAt(currentPoint +
                                    keywordsArray[indexArray].length())))) {
                        entranceArray[indexArray] = entranceArray[indexArray] + 1;
                    }
                    currentPosition = currentPoint+1;
            }
            newData.replaceAll(keywordsArray[indexArray], " ");
        }
        return entranceArray;
    }
}
