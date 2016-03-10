package t01.model.impl;

import t01.model.DirectoryInfo;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;

public class DirectoryInfoImpl implements DirectoryInfo {
    private DirectoryStream<Path> dirStream;
    private int countFile = 0;
    private int countDir = 0;
    private long sizeFile = 0;

    @Override
    public String getDirInfo(final String path) throws IllegalArgumentException {
        if (path == null) {
            throw new IllegalArgumentException("Path can not be NULL");
        }

        StringBuilder builder = new StringBuilder();
        try {
            dirStream = Files.newDirectoryStream(Paths.get(path));
            for (Path dir : dirStream) {
                File file = new File(dir.toString());
                builder.append(formatterTime(dir)).append("\t");
                if (file.isDirectory()) {
                    builder.append("<DIR>").append("\t\t");
                    countDir++;
                } else {
                    builder.append(alignFileSize(dir));
                    countFile++;
                    sizeFile += Files.size(dir);
                }
                builder.append("\t").append(file.getName()).append("\n");
            }
            builder.append("\t\t\t\t").append(countDir).append(" directories");
            builder.append("\n\t\t\t\t").append(countFile).append(" files ").append(String.format("%,8d", sizeFile)).append(" byte");
        } catch (InvalidPathException e) {
            throw new IllegalArgumentException("Path not found");
        } catch (IOException e) {
            throw new IllegalArgumentException("Can not access to path: " + path);
        }
        return builder.toString();
    }

    private String formatterTime(Path dir) throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy  HH.mm.ss");
        FileTime fileTime = Files.getLastModifiedTime(dir);
        return dateFormat.format(fileTime.toMillis());
    }

    private String alignFileSize(Path dir) throws IOException {
        StringBuilder builder = new StringBuilder();
        int length = String.format("%,8d", Files.size(dir)).length();
        for (int index = 0; index < 15 - length; index++) {
            builder.append(" ");
        }
        builder.append(String.format("%,8d", Files.size(dir)));
        return builder.toString();
    }
}