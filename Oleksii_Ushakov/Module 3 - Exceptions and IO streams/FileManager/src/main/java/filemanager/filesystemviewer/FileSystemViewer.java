package filemanager.filesystemviewer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Alexey Ushakov
 */
public class FileSystemViewer {
    public static String[] viewFolder(String start) throws IOException {
        ViewerVisitor visitor = new ViewerVisitor();
        Path startPath = Paths.get(start).toAbsolutePath();

        PathTree.getInstance().reset();
        Files.walkFileTree(startPath, visitor);

        return PathTree.getInstance().getPathTree();
    }
}
