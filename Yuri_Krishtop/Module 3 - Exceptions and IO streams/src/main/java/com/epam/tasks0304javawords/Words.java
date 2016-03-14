package com.epam.tasks0304javawords;

abstract class Words {
    protected static String currentDir;
    protected String[] words;
    protected String fileName;

    static {
        currentDir = "./src/main/java/com/epam/tasks0304javawords/";
    }

    public Words(String fileName) {
        this.fileName = currentDir + fileName;
    }

    abstract void loadWords();

    public String compareWords() {
        if (words == null) {
            loadWords();
        }
        int countJW = 0;
        String currentJavaWords = "";
        JavaWords javawords = new JavaWords("javaWords.txt");
        for (String w : words) {
            for (String jw : javawords.getWords()) {
                if (jw.equals(w)) {
                    currentJavaWords += (jw + ", ");
                    countJW++;
                    continue;
                }
            }
        }
        currentJavaWords = "In current file " + countJW + " words, such as: " + currentJavaWords;
        return currentJavaWords;
    }

    public String[] getWords() throws NullPointerException {
        loadWords();
        return words;
    }
}
