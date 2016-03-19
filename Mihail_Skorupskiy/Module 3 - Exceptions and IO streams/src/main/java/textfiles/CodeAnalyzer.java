package textfiles;

public class CodeAnalyzer {

    private static final String[] keywords = {
        "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const",
        "continue", "default", "do", "double", "else", "enum", "extends", "final", "finally", "float",
        "for", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long", "native",
        "new", "package", "private", "protected", "public", "return", "short", "static", "strictfp",
        "super", "switch", "synchronized", "this", "throw", "throws", "transient", "try", "void",
        "volatile", "while", "false", "null", "true",};

    private static int[] keywordsAmount = new int[keywords.length];

    public static String[] analyzeString(String code) throws NullPointerException{
        reset();
        if (code != null){
            String[] temp = code.split("[^a-z]");
            for (int i = 0; i < keywords.length; i++){
                for (int j = 0; j < temp.length; j++){
                    if (temp[j].contains(keywords[i])){
                        keywordsAmount[i]++;
                    }
                }
            }
            return generateResults();
        } else {
            throw new NullPointerException("Empty input.");
        }
    }

    public static String[] analyzeByteArray(int[] code) throws NullPointerException{
        if (code != null){
            String temp = new String();
            for (int i = 0; i < code.length; i++) {
                temp += (char)code[i];
            }
            return analyzeString(temp);
        } else {
            throw new NullPointerException("Empty input.");
        }
    }

    private static String[] generateResults(){
        String[] temp = new String[keywords.length];
        for (int i = 0; i < keywords.length; i++){
            temp[i] = keywords[i] + " is present " + keywordsAmount[i] + " times.";
        }
        return temp;
    }

    private static void reset(){
        keywordsAmount = new int[keywords.length];
    }
}
