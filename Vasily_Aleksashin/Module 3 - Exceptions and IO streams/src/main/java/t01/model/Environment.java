package t01.model;

import t01.exception.ModelException;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Environment {

    private static String currentDir = getHomeDir();

    public static String getCurrentDir() {
        return currentDir;
    }

    public static String getUserName() {
        return System.getProperty("user.name");
    }

    public static void setCurrentDir(final String path) throws ModelException {
        if (path == null) {
            throw new ModelException("Path can not be NULL");
        }
        if (path.equals("")) {
            throw new ModelException("Path can not be EMPTY");
        }
        try {
            Path preparedPath = Environment.preparedPath(path);
            if (Files.exists(preparedPath)) {
                currentDir = preparedPath.toString();
            } else {
                throw new ModelException(String.format("Path: '%s' not found", path));
            }
        } catch (InvalidPathException e) {
            throw new ModelException(String.format("Path: '%s' not found", path));
        }
    }

    public static String getHostName() {
        String host;
        try {
            host = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            host = "UNKNOWN";
        }
        return host;
    }

    public static String getHomeDir() {
        return System.getProperty("user.home");
    }

    public static Path preparedPath(final String path) throws ModelException {
        if (path == null) {
            throw new ModelException("Path can not be NULL");
        }
        if (path.length() == 0) {
            throw new ModelException("Path not found");
        }
        if (path.equals("..")) {
            return Paths.get(currentDir).getParent();
        }
        if (path.equals(".")) {
            return Paths.get(currentDir);
        }
        String result;
        if (path.startsWith("." + File.separator)) {
            result = currentDir + path.substring(1);
        } else if (path.startsWith(".." + File.separator)) {
            result = Paths.get(currentDir).getParent().toString() + path.substring(1);
        } else {
            result = path;
        }
        if (result.contains("/") && File.separatorChar == '\\') {
            throw new ModelException("Path not found");
        }
        if (result.contains("\\") && File.separatorChar == '/') {
            throw new ModelException("Path not found");
        }
        if (result.contains("\\") && File.separatorChar == '\\') {
            result.replace('\\', File.separatorChar);
        }
        if (result.charAt(result.length() - 1) != File.separatorChar) {
            result += File.separatorChar;
        }

        Path tempPath = Paths.get(result);

        if (!tempPath.isAbsolute()) {
            return Paths.get(currentDir, File.separator, result);
        }

        return tempPath;
    }
}
