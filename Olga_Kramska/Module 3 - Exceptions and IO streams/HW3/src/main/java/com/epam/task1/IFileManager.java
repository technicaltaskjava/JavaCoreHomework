package com.epam.task1;

/**
 * Created by Olga Kramska on 13-Mar-16.
 */
public interface IFileManager {

    void printDirContent();

    boolean makeFile(String fileName);

    boolean deleteFile(String fileName);

    void updateFile(String fileName, String text);
}
