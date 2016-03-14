package filemanager.filesystemviewer;

import java.nio.file.Path;
import java.util.Arrays;

/**
 * @author Alexey Ushakov
 */
class PathTree {
    private static PathTree INSTANCE = new PathTree();
    private static String[] pathTree = new String[100];
    private static int pathCount = 0;

    private PathTree() {
    }

    public static PathTree getInstance() {
        return INSTANCE;
    }

    public String[] getPathTree() {
        return Arrays.copyOfRange(pathTree, 0, pathCount);
    }

    public void addPathToTree(Path path) {
        if (pathCount == pathTree.length) {
            resizeTree();
        }
        pathTree[pathCount] = path.toAbsolutePath().toString();
        pathCount++;
    }

    public void reset() {
        pathCount = 0;
        pathTree = new String[100];
    }

    private void resizeTree() {
        String[] newPathTree = new String[pathCount * 2];
        System.arraycopy(pathTree, 0, newPathTree, 0, pathCount);
        pathTree = newPathTree;
    }
}
