//package t03t04.javafilereader.wordchecker;
//
///**
// * @author Alexey Ushakov
// */
//
//public class ReservedWordChecker {
//    //From Java.SE.01.JavaFundamentals conspect.pdf
//    private static String[] words = {"abstract", "continue", "for", "new",
//            "switch", "assert", "default", "goto", "package", "synchronized",
//            "boolean", "do", "if", "private", "this", "break", "double",
//            "implements", "protected", "throw", "byte", "else", "import",
//            "public", "throws", "case", "enum", "instanceof", "return",
//            "transient", "catch", "extends", "int", "short", "try", "char",
//            "final", "interface", "static", "void", "class", "finally", "long",
//            "strictfp", "volatile"
//    };
//
//    public static int length() {
//        return words.length;
//    }
//
//    public static boolean isReservedWord(String word) {
//        for (String s : words) {
//            if (word.equals(s)) {
//                return true;
//            }
//        }
//        return false;
//    }
//}
