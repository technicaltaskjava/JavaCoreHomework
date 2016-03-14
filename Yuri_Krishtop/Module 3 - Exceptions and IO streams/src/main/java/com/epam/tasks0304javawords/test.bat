    public void writeWordsToFile(String newFileName) throws IOException {
        if (words == null) {
            loadWords();
        }
        int countJW = 0;
        String currentJavaWords = "";
        JavaWords javawords = new JavaWords("javaWords.txt");
        for (String w : words) {
            for (String jw : javawords.getWords()) {
                if (jw.equals(w)) {
                    currentJavaWords = currentJavaWords +jw+", ";
                    countJW++;
                    continue;
                }
            }
        }
        currentJavaWords = "In current file "+countJW+" words, such as: " + currentJavaWords;
        FileOutputStream myFile = new FileOutputStream("./src/main/resources/" +newFileName);
        Writer out = new BufferedWriter(new OutputStreamWriter(myFile, "UTF8"));
        out.write(currentJavaWords);
        out.close();
    }


}