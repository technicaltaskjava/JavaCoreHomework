package htmlimagesearch;

import htmlimagesearch.exceptions.NotFileException;
import htmlimagesearch.exceptions.NotReadableException;

import java.io.*;
import java.util.Arrays;

/**
 * @author Alexey Ushakov
 */
public class HTMLImageSearch {
    private String[] linksOnImage;
    private int linksCount;
    private static final String LINK_PATTERN = "(.*\\([Рр]ис.\\s[\\d]+((( и(ли)? )\\d+)?)*\\).*|.* [Рр]исунке \\d+.*)";
    private static final String LINE_END_PATTERN = "[.?!]+\\s+(?<!([Рр]ис.\\s))";
    private static final String CUSTOM_TAG_PATTERN = "(</?\\w+>|&.+;)";

    public HTMLImageSearch() {
        this.linksOnImage = new String[100];
        this.linksCount = 0;
    }

    private void resizeLinks() {
        String[] newLinks = new String[linksOnImage.length * 2];
        System.arraycopy(linksOnImage, 0, newLinks, 0, linksOnImage.length);
        linksOnImage = newLinks;
    }

    private void addToLinksOnImage(String line) {
        if (linksCount == linksOnImage.length) {
            resizeLinks();
        }

        linksOnImage[linksCount] = line;
        linksCount++;
    }

    private void checkFile(File file) throws IOException {
        if (!file.exists()) {
            throw new FileNotFoundException("File " + file + " not exist");
        }
        if (!file.isFile()) {
            throw new NotFileException("Not a file " + file);
        }
        if (!file.canRead()) {
            throw new NotReadableException("Can`t read file " + file);
        }
    }

    public void load(String path) throws IOException {
        File file = new File(path);
        checkFile(file);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();

            while (line != null && !line.contains("<body>")) {
                line = reader.readLine();
            }

            while (line != null) {
                if (line.matches(LINK_PATTERN)) {
                    String[] sentences = line.split(LINE_END_PATTERN);
                    for (String sentence : sentences) {
                        if (sentence.matches(LINK_PATTERN)) {
                            addToLinksOnImage(sentence.replaceAll(CUSTOM_TAG_PATTERN, ""));
                        }
                    }
                }
                line = reader.readLine();
            }
        }
    }

    public String[] getLinksOnImage() {
        return Arrays.copyOfRange(linksOnImage, 0, linksCount);
    }
}
