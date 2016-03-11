package t01.model;

import t01.exception.ModelException;

/**
 * Created by aleksashin on 09.03.2016.
 */
public interface ShellPrompt {
	String getCurrentDir();

    void setCurrentDir(String path) throws ModelException;

    String getPrompt();
}
