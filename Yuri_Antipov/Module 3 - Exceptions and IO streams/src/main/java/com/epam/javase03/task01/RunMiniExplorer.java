package com.epam.javase03.task01;

import com.epam.javase03.task01.miniExplorer.MiniExplorer;
import com.epam.javase03.task01.reader.Reader;
import com.epam.javase03.task01.writer.Writer;

public class RunMiniExplorer {
    public static void main(String[] args) {
        MiniExplorer explorer = new MiniExplorer();
        Writer writerFile = new Writer();
        Reader readerFile = new Reader();
        explorer.createNewDirectory();
        explorer.showDirectoryList();
        explorer.getDirectoryPath();
        explorer.createFile();
        writerFile.writeToFile(explorer.getFile());
        readerFile.readFile(explorer.getFile());
        explorer.deleteFile();
    }
}
